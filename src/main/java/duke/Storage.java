package duke;

import duke.exception.DukeInvalidFileContentException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Storage {
    protected static final Path FILE_PATH = Path.of("data/duke.txt");
    protected static boolean isLoadFile;
    private Ui ui;
    private Parser parser = new Parser();

    public Storage() {
    }

    /**
     * Checks whether the data directory exists and whether the duke.txt
     * file exists. If not, create the directory/file appropriately.
     *
     * @throws IOException If file operation failed.
     */
    public static void checkFileExists() throws IOException {
        Path dataDirectory = FILE_PATH.getParent();
        if (!Files.isDirectory(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }

        if (Files.notExists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
        }
    }

    /**
     * Loads or populate the data file into the Duke program
     * and handles exception such as IOException and
     * also invalid format content for the data file.
     *
     * @param taskManager the object that manages task operations on tasks.
     * @throws IOException If file operation failed.
     */
    public static void loadDukeDataFile(TaskList taskManager) throws IOException,
            ArrayIndexOutOfBoundsException, NumberFormatException, DukeInvalidFileContentException {
        isLoadFile = true;
        checkFileExists();
        try {
            populateDukeFromDataFile(taskManager);
        } catch (IOException e) {
            System.out.println("Unable to populate data...");
        }
        isLoadFile = false;
    }

    /**
     * This method reads all the contents of the data file line by line.
     * and extracts the task type, task mark status and task description.
     * It then converts/builds that data file line into an appropriate command to be supplied
     * to the terminal and modifies the mark status of the task appropriately.
     *
     * @param taskManager the object that manages task operations on tasks.
     * @throws IOException If file operation failed.
     * @throws DukeInvalidFileContentException If data file is of invalid format.
     * @throws NumberFormatException If task number could not be parsed into an integer.
     * @throws ArrayIndexOutOfBoundsException For any operations which involves index checking.
     */
    public static void populateDukeFromDataFile(TaskList taskManager) throws IOException,
            DukeInvalidFileContentException, NumberFormatException, ArrayIndexOutOfBoundsException {
        List<String> fileContentLines = Files.readAllLines(FILE_PATH);
        for (String lines:fileContentLines) {
            String[] arrayOfContentsInALine = lines.split("\\|");
            if (arrayOfContentsInALine.length < 1) {
                throw new DukeInvalidFileContentException();
            }
            String taskType = arrayOfContentsInALine[0].trim();
            int taskMarkStatus = Integer.parseInt(arrayOfContentsInALine[1].trim());
            try {
                //Converts task in data file format to a task in command line format.
                String command = buildTaskCommand(taskType, arrayOfContentsInALine);
                switch (taskType) {
                case "T":
                    Parser.performTodo(taskManager, command);
                    break;
                case "D":
                    Parser.performTaskWithTimeConstraints(taskManager, command, "/by ", "deadline");
                    break;
                case "E":
                    Parser.performTaskWithTimeConstraints(taskManager, command, "/at ", "event");
                    break;
                default:
                    throw new DukeInvalidFileContentException();
                }
                //Modifies the mark status of each task appropriately
                populateDukeTaskMarkStatus(taskManager, taskMarkStatus);
            } catch (DukeInvalidFileContentException e) {
                System.out.println("Invalid file content...");
            }
        }
    }

    /**
     * Modifies the mark status of the task appropriately by calling
     * the performMarking method.
     *
     * @param taskManager the object that manages task operations on tasks.
     * @param taskMarkStatus either '1' or '0'. '1' indicates the task is marked and '0' is unmarked.
     */
    public static void populateDukeTaskMarkStatus(TaskList taskManager, int taskMarkStatus) {
        if (taskMarkStatus == 1) {
            String buildMarkCommand = "mark " + Task.getNumberOfTasks();
            Parser.performMarking(taskManager, buildMarkCommand, true, "mark");
        }
    }

    /**
     * This method builds or converts the task in data file format to task
     * in command line format that is to be supplied to the terminal of the program.
     *
     * @param taskType the task type from data file in 'T', 'D' or 'E'.
     * @param arrayOfContentsInALine stores task description with its deadline time if any.
     * @return the finalized command line to be supplied to terminal.
     * @throws ArrayIndexOutOfBoundsException For any operations which involves index checking.
     * @throws DukeInvalidFileContentException If data file is of invalid format.
     */
    public static String buildTaskCommand(String taskType, String[] arrayOfContentsInALine) throws
            ArrayIndexOutOfBoundsException, DukeInvalidFileContentException {
        String finalizedCommand = "";
        switch (taskType) {
        case "T":
            String todoDescription = arrayOfContentsInALine[2];
            finalizedCommand = "todo" + todoDescription;
            break;
        case "D":
            String deadlineDescription = arrayOfContentsInALine[2];
            String deadlineTime = arrayOfContentsInALine[3];
            finalizedCommand = "deadline" + deadlineDescription + "/by" + deadlineTime;
            break;
        case "E":
            String eventDescription = arrayOfContentsInALine[2];
            String eventTime = arrayOfContentsInALine[3];
            finalizedCommand = "event" + eventDescription + "/at" + eventTime;
            break;
        default:
            throw new DukeInvalidFileContentException();
        }
        return finalizedCommand;
    }

    /**
     * Saves new task added from terminal into the duke data file.
     * This method converts the task command line format to the task
     * data file format so that it could be stored in the duke data file.
     *
     * @param newTask the new task to be added to the duke data file.
     * @throws IOException If file operation failed.
     * @throws ArrayIndexOutOfBoundsException For any operations which involves index checking.
     */
    public static void appendDukeDataFile(String newTask) throws IOException, ArrayIndexOutOfBoundsException {
        String contentToAppend = "";
        String fetchTaskType = newTask.split("]")[0].replaceAll("[^a-zA-Z0-9]", "");
        String fetchTaskDescription = newTask.split("]")[2].trim();

        if (fetchTaskDescription.contains("(by:")) {
            String formattedTaskDescription = fetchTaskDescription.split("\\(by:")[0].trim();
            String fetchTime = fetchTaskDescription.split("\\(by:")[1].replaceAll("[^a-zA-Z0-9\\s\\s]", "");
            contentToAppend += (fetchTaskType + " | 0 | " + formattedTaskDescription + " |" + fetchTime + "\n");

        } else if (fetchTaskDescription.contains("(at:")) {
            String formattedTaskDescription = fetchTaskDescription.split("\\(at:")[0].trim();
            String fetchTime = fetchTaskDescription.split("\\(at:")[1].replaceAll("[^a-zA-Z0-9\\s\\s]", "");
            contentToAppend += (fetchTaskType + " | 0 | " + formattedTaskDescription + " |" + fetchTime + "\n");

        } else {
            contentToAppend += (fetchTaskType + " | 0 | " + fetchTaskDescription + "\n");
        }
        Files.write(FILE_PATH, contentToAppend.getBytes(), StandardOpenOption.APPEND);
    }

    /**
     * This method modifies the mark status in the duke data file
     * by following the task number supplied. The task number supplied
     * relates to the line number in the duke data file to be modified.
     * Makes use of the modifyMarkedLine method to aid it.
     *
     * @param taskNumber the task number or integer to check.
     * @param isMarked true if task is to be marked, false if it is to be unmarked.
     * @throws IOException If file operation failed.
     * @throws ArrayIndexOutOfBoundsException For any operations which involves index checking.
     */
    public static void modifyMarkStatusDataFile(int taskNumber, boolean isMarked) throws
            IOException, ArrayIndexOutOfBoundsException {
        List<String> fileContentLines = Files.readAllLines(FILE_PATH);
        int lineNumber = 1;
        String replacedFileContents = "";
        for (String lines:fileContentLines) {
            if (taskNumber == lineNumber) {
                String newChangedLine = modifyMarkedLine(lines, isMarked);
                replacedFileContents += (newChangedLine + "\n");
            } else {
                replacedFileContents += (lines + "\n");
            }
            lineNumber += 1;
        }
        Files.write(FILE_PATH, replacedFileContents.getBytes());
    }

    /**
     * This methods changes the mark status of the task by modifying
     * its mark status in the data file to either '1' as marked, and
     * '0' as unmarked.
     *
     * @param lineToModify modifies the mark status of the task line.
     * @param isMarked true if task is to be marked, false if it is to be unmarked.
     * @return the finalized modified line to be placed into the duke data file.
     * @throws ArrayIndexOutOfBoundsException For any operations which involves index checking.
     */
    public static String modifyMarkedLine(String lineToModify, boolean isMarked) throws ArrayIndexOutOfBoundsException {
        String finalLine = "";
        String[] arrayOfLineToBeModified = lineToModify.split("\\|");
        if (isMarked) {
            arrayOfLineToBeModified[1] = " 1 ";
        } else {
            arrayOfLineToBeModified[1] = " 0 ";
        }

        for (int i = 0; i < arrayOfLineToBeModified.length; i += 1) {
            if (i == (arrayOfLineToBeModified.length - 1)) {
                finalLine += (arrayOfLineToBeModified[i]);
            } else {
                finalLine += (arrayOfLineToBeModified[i] + "|");
            }
        }
        return finalLine;
    }

    /**
     * This method removes the line of the task to be deleted
     * from the duke data file.
     *
     * @param taskNumberToDelete the task number (in integer) to delete from task list.
     * @throws IOException If file operation failed.
     */
    public static void deleteTaskFromDataFile(int taskNumberToDelete) throws IOException {
        List<String> fileContentLines = Files.readAllLines(FILE_PATH);
        int lineNumber = 1;
        String replacedFileContents = "";
        for (String lines:fileContentLines) {
            if (taskNumberToDelete != lineNumber) {
                replacedFileContents += (lines + "\n");
            }
            lineNumber += 1;
        }
        Files.write(FILE_PATH, replacedFileContents.getBytes());
    }

    /**
     * This method checks whether it is initially a load file or not.
     * If it is not a load file, the task messages are printed normally
     * to terminal and saves the task added into the duke data file.
     *
     * @param taskManager the object that manages task operations on tasks.
     * @param newTask the new task to be added to the duke data file.
     */
    public static void saveDataAddToListOperation(TaskList taskManager, Task newTask) {
        if (!isLoadFile) {
            taskManager.addTaskPrintMessage(newTask);
            try {
                appendDukeDataFile(newTask.toString());
            } catch (IOException e) {
                System.out.println("Unable to update duke file.");
            }
        }
    }

    /**
     * This method checks whether it is initially a load file or not.
     * If it is not a load file, the mark task messages are printed normally
     * to terminal and modifies the task mark status of the duke data file.
     *
     * @param taskManager the object that manages task operations on tasks.
     * @param taskNumberMarked the task number to be marked or unmarked.
     * @param isMarked true if task is to be marked, false if it is to be unmarked.
     */
    public static void saveDataChangeMarkStatusOperation(TaskList taskManager, int taskNumberMarked, boolean isMarked) {
        if (!isLoadFile) {
            taskManager.markStatusPrintMessage(taskNumberMarked, isMarked);
            try {
                modifyMarkStatusDataFile(taskNumberMarked, isMarked);
            } catch (IOException e) {
                System.out.println("Unable to update file.");
            }
        }
    }

    /**
     * For aiding, this method simply performs the deleteTaskFromDataFile function
     * and handles the exception in case the task could not be deleted from the file.
     *
     * @param taskManager the object that manages task operations on tasks.
     * @param taskNumberToDelete the task number to be deleted.
     */
    public static void saveDataDeleteFromListOperation(TaskList taskManager, int taskNumberToDelete) {
        try {
            deleteTaskFromDataFile(taskNumberToDelete);
        } catch (IOException e) {
            System.out.println("Unable to delete from file.");
        }
    }

}
