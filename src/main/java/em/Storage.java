package em;

import EmProgram.exception.InvalidUserInputException;
import EmProgram.exception.StorageException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static EmProgram.exception.InvalidUserInputException.INVALID_INPUT;
import static EmProgram.exception.StorageException.INVALID_FILE_INPUT;
import static EmProgram.exception.StorageException.IO_EXCEPTION;

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
     * @throws StorageException          If input and/or output have error.
     * @throws FileNotFoundException     If input and/or output have error.
     * @throws InvalidUserInputException If input and/or output have error.
     * @throws IOException               If input and/or output have error.
     */
    public static ArrayList populateFileContents() throws FileNotFoundException, InvalidUserInputException, StorageException {
        List<String> fileContentLines = null;
        try {
            fileContentLines = Files.readAllLines(databasePath);
            for (String lines : fileContentLines) {
                isFileContentValid(lines);
                String userInput = null;
                String[] contentsInALine = lines.split("\\|", -1);
                if (contentsInALine.length < 1) {
                    throw new InvalidUserInputException(INVALID_INPUT);
                }
                switch (contentsInALine[0].trim()) {
                case "T":
                    userInput = "todo " + contentsInALine[2].trim();
                    TaskList.addTask(userInput, Duke.taskArrayList, false);
                    break;
                case "D":
                    userInput = "deadline " + contentsInALine[2].trim() + " " + "/by " + contentsInALine[3].trim();
                    TaskList.addTaskAndTime(userInput, Duke.taskArrayList, false);
                    break;
                case "E":
                    userInput = "event " + contentsInALine[2].trim() + " " + "/at " + contentsInALine[3].trim();
                    TaskList.addTaskAndTime(userInput, Duke.taskArrayList, false);
                    break;
                default:
                    throw new StorageException(INVALID_FILE_INPUT);
                }
                if (contentsInALine[1].trim().equalsIgnoreCase("1")) {
                    Duke.taskArrayList.get(Duke.taskArrayList.size() - 1).markAsDone(Duke.taskArrayList.size(), Duke.taskArrayList, false);
                }
            }
        } catch (IOException e) {
            System.out.println(IO_EXCEPTION);
        }
        return Duke.taskArrayList;
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
     * <p>
     * If the task is being deleted, the task details in the file will be replaced as
     * an empty string. The empty strings will be removed by the removeSpaces method.
     * If the task is being marked/unmarked, the task details in
     * the file will be modified to reflect the corresponding changes.
     *
     * @param taskNumber number of the task in the task list to be modified.
     * @param isMark     whether the task is being marked or unmarked.
     * @param isDelete   whether the task is being deleted.
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
     * @param isMark     whether the task is being marked or unmarked.
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
     * Overwrite certain lines in the database file.
     * Look through the whole database file and find the string that
     * matches the task to overwrite. Removes any empty spaces in between the lines
     * after file is being modified.
     *
     * @param lineNumber number of the line to be replaced in the database file.
     * @param newLine    command with the right date and time format.
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

    /**
     * Check for the validity of the file contents.
     * Look through the whole database file and check the validity of each line.
     * It will check for all the validations of the input and ensure all fulfilled the
     * validations.
     *
     * @param fileCommand Command in the file.
     * @throws StorageException If command in the file is invalid.
     */
    public static void isFileContentValid(String fileCommand) throws StorageException {
        String[] fileCommandArray = fileCommand.split("\\|");
        switch (fileCommandArray[0]) {
        case "T":
            if (fileCommandArray.length < 3) {
                printFileCorrupted();
            }
            break;
        case "E":
        case "D":
            if (fileCommandArray.length < 4) {
                printFileCorrupted();
            }
            isTimeAndDateInFileValid(fileCommandArray[3]);
            break;
        default:
            throw new StorageException(INVALID_FILE_INPUT);
        }
    }

    /**
     * Check for the validity of the time in the file command.
     * Check if the time contains ":, AM, PM" and also whether the
     * hour and minutes of the time in the file command satisfy the
     * conditions of a time.
     *
     * If time failed to meet the conditions stated, an error message will
     * be printed and the program will be terminated for file correction.
     *
     * @param Time Time found in Command in the file.
     */
    public static void isTimeValid(String Time) {
        String[] TimeArray = Time.split(":"); //TimeArray[0] contain hour
        String[] minArray = null;
        if (Integer.parseInt(TimeArray[0]) > 12 || Integer.parseInt(TimeArray[0]) <= 0) {
            printFileCorrupted();
            return;
        }

        if (TimeArray[1].contains("AM")) {
            minArray = TimeArray[1].split("AM"); //minArray[0] contains min
        } else if (TimeArray[1].contains("PM")) {
            minArray = TimeArray[1].split("PM");
        } else {
            printFileCorrupted();
            return;
        }

        if (Integer.parseInt(minArray[0]) > 59) {
            printFileCorrupted();
            return;
        }
    }

    /**
     * Check for the validity of the date in the file command.
     * Check if the date is in "MMM-d-yyyy" format.
     *
     * If date failed to meet the conditions stated, an error message will
     * be printed and the program will be terminated for file correction.
     *
     * @param inDate Date found in Command in the file.
     */
    public static void isDateInFileValid(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-d-yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            printFileCorrupted();
            return;
        }
    }

    /**
     * Check for the validity of both the time and date in the file command.
     * Check if the time contains ":, AM, PM" and also whether the
     * hour and minutes of the time in the file command satisfy the
     * conditions of a time.
     * Check if the date is in "MMM-d-yyyy" format.
     * Ensure that both date and time exist in the field for both event and deadline.
     *
     * If time or date failed to meet the conditions stated, an error message will
     * be printed and the program will be terminated for file correction.
     *
     * @param DateAndTime Time found in Command in the file.
     */
    public static void isTimeAndDateInFileValid(String DateAndTime) {
        String[] DateAndTimeArray = DateAndTime.split(" ");
        if (DateAndTimeArray.length <= 1) {
            printFileCorrupted();
        }
        isDateInFileValid(DateAndTimeArray[0].trim());
        isTimeValid(DateAndTimeArray[1].trim());

    }

    /**
     * Terminates the program if file contents is corrupted.
     * If the content in the file is not stored in the correct format,
     * the program will be terminated. It will also print an error message
     * to remind the user to check the content in the file.
     */
    public static void printFileCorrupted() {
        System.out.println(INVALID_FILE_INPUT);
        System.exit(0);
    }
}

