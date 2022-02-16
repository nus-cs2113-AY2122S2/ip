package duke;

import duke.exception.DukeInvalidFileContentException;
import duke.task.Task;
import duke.task.TaskManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileManager {
    protected static final Path FILE_PATH = Path.of("data/duke.txt");
    protected static boolean isLoadFile;
    protected static Duke dukeProgram = new Duke();

    public FileManager() {
    }

    public static void checkFileExists() throws IOException {
        Path dataDirectory = FILE_PATH.getParent();
        if (!Files.isDirectory(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }

        if (Files.notExists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
        }
    }

    public static void loadDukeDataFile(TaskManager taskManager) throws IOException {
        isLoadFile = true;
        checkFileExists();
        try {
            populateDukeFromDataFile(taskManager);
        } catch (IOException e) {
            System.out.println("Unable to populate data...");
        } catch (DukeInvalidFileContentException e) {
            System.out.println("Invalid file content...");
        }
        isLoadFile = false;
    }

    public static void populateDukeFromDataFile(TaskManager taskManager) throws IOException, DukeInvalidFileContentException, NumberFormatException, ArrayIndexOutOfBoundsException {
        List<String> fileContentLines = Files.readAllLines(FILE_PATH);
        for (String lines:fileContentLines) {
            String[] arrayOfContentsInALine = lines.split("\\|");
            if (arrayOfContentsInALine.length < 1) {
                throw new DukeInvalidFileContentException();
            }
            String taskType = arrayOfContentsInALine[0].trim();
            int taskMarkStatus = Integer.parseInt(arrayOfContentsInALine[1].trim());
            try {
                String command = buildTaskCommand(taskType, arrayOfContentsInALine);
                switch (taskType) {
                case "T":
                    dukeProgram.performTodo(taskManager, command);
                    break;
                case "D":
                    dukeProgram.performTaskWithTimeConstraints(taskManager, command, "/by ", "deadline");
                    break;
                case "E":
                    dukeProgram.performTaskWithTimeConstraints(taskManager, command, "/at ", "event");
                    break;
                default:
                    throw new DukeInvalidFileContentException();
                }
                populateDukeTaskMarkStatus(taskManager, taskMarkStatus);
            } catch (DukeInvalidFileContentException e) {
                System.out.println("Invalid file content...");
            }
        }
    }

    public static void populateDukeTaskMarkStatus(TaskManager taskManager, int taskMarkStatus) {
        if (taskMarkStatus == 1) {
            String buildMarkCommand = "mark " + Task.getNumberOfTasks();
            dukeProgram.performMarking(taskManager, buildMarkCommand, true, "mark");
        }
    }

    public static String buildTaskCommand(String taskType, String[] arrayOfContentsInALine) throws ArrayIndexOutOfBoundsException, DukeInvalidFileContentException {
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

    public static void modifyMarkStatusDataFile(int taskNumber, boolean isMarked) throws IOException, ArrayIndexOutOfBoundsException {
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

    public static void saveDataAddToListOperation(TaskManager taskManager, Task newTask) {
        if (!isLoadFile) {
            taskManager.addTaskPrintMessage(newTask);
            try {
                appendDukeDataFile(newTask.toString());
            } catch (IOException e) {
                System.out.println("Unable to update duke file.");
            }
        }
    }

    public static void saveDataChangeMarkStatusOperation(TaskManager taskManager, int taskNumberMarked, boolean isMarked) {
        if (!isLoadFile) {
            taskManager.markStatusPrintMessage(taskNumberMarked, isMarked);
            try {
                modifyMarkStatusDataFile(taskNumberMarked, isMarked);
            } catch (IOException e) {
                System.out.println("Unable to update file.");
            }
        }
    }

    public static void saveDataDeleteFromListOperation(TaskManager taskManager, int taskNumberToDelete) {
        try {
            deleteTaskFromDataFile(taskNumberToDelete);
        } catch (IOException e) {
            System.out.println("Unable to delete from file.");
        }
    }

}
