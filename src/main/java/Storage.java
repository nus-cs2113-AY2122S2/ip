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
import static em.exception.StorageException.IO_EXCEPTION;

public class Storage extends Duke {
    private static TaskList tasks;
    public static Path databasePath;

    public Storage(Path databasePath) {
        Storage.databasePath = databasePath;
    }

    /**
     * Populate all the task details in the database file at the start of the program. To restore the
     * data that was previously saved in the file.
     * Iterate through the database file, process and add the task in the file into taskArrayList.
     * Iterate through each line in the file and convert the task details to the correct
     * format to be processed and be added into the taskArrayList.
     * It checks for the correct date and time format as well as ensuring the mark status of the
     * task is being reflected correctly from the database file into the taskArrayList.
     *
     * @return taskArrayList to store all the tasks being processed from the database file.
     * @throws StorageException If input and/or output have error.
     * @throws FileNotFoundException If input and/or output have error.
     * @throws InvalidUserInputException If input and/or output have error.
     * @throws IOException If input and/or output have error.
     */
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
            System.out.println(IO_EXCEPTION);
        }
        return taskArrayList;
    }

    /**
     * Add new task details into the database file by appending the new task details
     * into the last line of the file.
     *
     * @param textToAppend number of the task in the task list to be marked/unmarked.
     * @throws IOException If input and/or output have error.
     */
    public static void writeToFile(String textToAppend) throws IOException {
        String modifiedContent = System.lineSeparator() + textToAppend;
        Files.write(databasePath, modifiedContent.getBytes(), StandardOpenOption.APPEND);
        removeSpaces();
    }

    /**
     * Iterate through the database file and remove any empty lines in the file.
     */
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

    /**
     * Go to the line (taskNumber) in the file that stores the task details and modifies
     * its content and saved it back into the file.
     * If task is marked, the mark status of the file will be represented as "1" and
     * if the task is unmarked, the mark status of the file will be represented as "0".
     *
     * If the task is being deleted, the task details in the file will be replaced as
     * an empty string. The empty strings will be removed by the removeSpaces method.
     * If the task is being marked/unmarked, the task details in
     * the file will be modified to reflect the corresponding changes.
     *
     * @param taskNumber number of the task in the task list to be modified.
     * @param isMark whether the task is being marked or unmarked.
     * @param isDelete whether the task is being deleted.
     * @throws IOException If input and/or output have error.
     */
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

    /**
     * Return the updated string of the task details after mark status
     * of the task is modified in the task list.
     * Go to the line (taskNumber) in the file that stores the task details and modifies
     * its content.
     * If task is marked, the mark status of the file will be represented as "1" and
     * if the task is unmarked, the mark status of the file will be represented as "0".
     *
     * @param taskNumber number of the task in the task list to be marked/unmarked.
     * @param isMark whether the task is being marked or unmarked.
     * @return newLine the updated task details after its been marked/unmarked.
     * @throws IOException If input and/or output have error.
     */
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
            System.out.println(StorageException.IO_EXCEPTION);
        }
        return newLine;
    }

    /**
     * Return an arrayList containing all the task details in the file that
     * has been converted to user command format.
     * Change the format of "D,1,return book, 2021-11-12 12:00PM" to "deadline 1 retyrn book /by 2021-11-12 12:00PM".
     *
     * @return processedContent the updated task details after it has been marked/unmarked.
     * @throws StorageException If the file content is being corrupted.
     * @throws InvalidUserInputException If the user command is invalid.
     * @throws IOException If input and/or output have error.
     */
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
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(IO_EXCEPTION);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        return processedContent;
    }

    /**
     * Overwrite certain lines in the database file.
     * Look through the whole database file and find the string that
     * matches the task to overwrite. Removes any empty spaces in between the lines
     * after file is being modified.
     *
     * @param lineNumber number of the line to be replaced in the database file.
     * @param newLine command with the right date and time format.
     * @throws IOException If input and/or output have error.
     */
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

    /**
     * Overwrite certain lines in the database file.
     * Replace the user command with the wrong format of time and date
     * to the correct format in the database file.
     *
     * @param oldLine command with the wrong date and time format.
     * @param newLine command with the right date and time format.
     * @param oldTime time and date string before the reformatting.
     */
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

    /**
     * Check if the database file exists.
     * If directory or file does not exist, it will create the corresponding directory and file.
     */
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
