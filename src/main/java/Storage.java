import em.exception.InvalidUserInputException;
import em.exception.StorageException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static em.exception.InvalidUserInputException.INVALID_INPUT;
import static em.exception.StorageException.INVALID_FILE_INPUT;

public class Storage extends Duke {
    private static TaskList tasks;
    public static Path databasePath;

    public Storage(Path databasePath) {
        Storage.databasePath = databasePath;
    }

    public static ArrayList populateFileContents() throws FileNotFoundException, InvalidUserInputException, StorageException {
        List<String> fileContentLines = null;
        try {
            fileContentLines = Files.readAllLines(databasePath);
            for (String lines : fileContentLines) {
                String userInput = null;
                String[] contentsInALine = lines.split(",", -1);
                if (contentsInALine.length < 1) {
                    throw new InvalidUserInputException(INVALID_INPUT);
                }
                switch (contentsInALine[0].trim()) {
                case "T":
                    userInput = "todo " + contentsInALine[2].trim();
                    TaskList.addTask(userInput, taskArrayList, false);
                    break;
                case "D":
                    userInput = "deadline " + contentsInALine[2].trim() + " " + "/by " + contentsInALine[3].trim();
                    TaskList.addTaskAndTime(userInput, taskArrayList, false);
                    break;
                case "E":
                    userInput = "event " + contentsInALine[2].trim() + " " + "/at " + contentsInALine[3].trim();
                    TaskList.addTaskAndTime(userInput, taskArrayList, false);
                    break;
                default:
                    throw new StorageException(INVALID_FILE_INPUT);
                }
                if (contentsInALine[1].trim().equalsIgnoreCase("1")) {
                    taskArrayList.get(taskArrayList.size() - 1).markAsDone(taskArrayList.size(), taskArrayList, false);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskArrayList;
    }

    public static void writeToFile(String textToAppend) throws IOException {
        String modifiedContent = System.lineSeparator() + textToAppend;
        Files.write(databasePath, modifiedContent.getBytes(), StandardOpenOption.APPEND);
        removeSpaces();
    }

    public static void removeSpaces() throws IOException {
        String content = "";
        List<String> fileContentLines = Files.readAllLines(databasePath);
        for (String lines : fileContentLines) {
            lines = lines.trim();
            if (lines.length() > 0) {
                content += lines + System.lineSeparator();
            }
            Files.write(databasePath, content.getBytes());
        }
    }

    public static void modifyDatabase(int taskNumber, Boolean isMark, Boolean isDelete) {
        String oldContent = "";
        String newLine = "";
        try {
            removeSpaces();
            if (isDelete) {
                newLine = "";
            } else {
                newLine = modifyContent(taskNumber, isMark);
            }
            overWrite(taskNumber, newLine);
        } catch (IOException e) {
            System.out.println(StorageException.IO_EXCEPTION);
        }
    }

    public static String modifyContent(int taskNumber, Boolean isMark) {
        String newLine = "";
        try {
            String oldLine = Files.readAllLines(databasePath).get(taskNumber - 1);
            if (isMark) {
                newLine = oldLine.replaceFirst("0", "1");
            } else {
                newLine = oldLine.replaceFirst("1", "0");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newLine;
    }

    public static ArrayList convertDatabaseInput() {
        List<String> fileContentLines = null;
        ArrayList<String> processedContent = new ArrayList<String>();
        try {
            fileContentLines = Files.readAllLines(databasePath);
            for (String lines : fileContentLines) {
                String userInput = null;
                String[] contentsInALine = lines.split(",", -1);
                if (contentsInALine.length < 1) {
                    throw new InvalidUserInputException(INVALID_INPUT);
                }
                switch (contentsInALine[0].trim()) {
                case "T":
                    userInput = "todo " + contentsInALine[1].trim() + " " + contentsInALine[2].trim();
                    processedContent.add(userInput);
                    break;
                case "D":
                    userInput = "deadline " + contentsInALine[1].trim() + " " + contentsInALine[2].trim() + " " + "/by " + contentsInALine[3].trim();
                    processedContent.add(userInput);
                    break;
                case "E":
                    userInput = "event " + contentsInALine[1].trim() + " " + contentsInALine[2].trim() + " " + "/at " + contentsInALine[3].trim();
                    processedContent.add(userInput);
                    break;
                default:
                    throw new StorageException(INVALID_FILE_INPUT);
                }
            }
        } catch (InvalidUserInputException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        }
        return processedContent;
    }

    public static void overWrite(int lineNumber, String newLine) {
        try {
            String oldContent = "";
            removeSpaces();
            List<String> fileContentLines = null;
            fileContentLines = Files.readAllLines(databasePath);
            int loopNumber = 1;
            for (String lines : fileContentLines) {
                if (loopNumber == lineNumber) {
                    oldContent += (newLine + "\n");
                } else {
                    oldContent += (lines + "\n");
                }
                loopNumber += 1;
            }
            Files.write(databasePath, oldContent.getBytes());
            removeSpaces();
        } catch (IOException e) {
            System.out.println(StorageException.IO_EXCEPTION);
        }
    }

    public static void replaceContent(String oldLine, String newLine, String oldTime) {
        ArrayList<String> processContent = convertDatabaseInput();
        String[] oldLineArray = oldLine.split(" ", 4);
        String[] newLineArray = newLine.split(",", 4);

        int lineNumber = 1;
        String realLine = null;
        for (String line : processContent) {
            String[] lineArray = line.split(" ", 5);
            if (lineArray[0].equals("event") || lineArray[0].equals("deadline")) {
                if (lineArray[0].equals(oldLineArray[0]) && lineArray[2].equals(oldLineArray[1]) && lineArray[4].equals(oldTime)) {
                    realLine = lineArray[1];
                    break;
                }
            }
            lineNumber += 1;
        }
        if (realLine.equals("1")) {
            newLineArray[1] = "1";
            newLine = newLineArray[0] + "," + newLineArray[1] + "," + newLineArray[2] + "," + newLineArray[3];
        }
        overWrite(lineNumber, newLine);
    }

    public static void checkFileExists() throws IOException {
        Path dataDirectory = databasePath.getParent();
        if (!Files.isDirectory(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }

        if (Files.notExists(databasePath)) {
            Files.createFile(databasePath);
        }
    }

}
