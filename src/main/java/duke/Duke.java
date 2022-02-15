package duke;

import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeMaxTaskException;
import duke.exception.DukeMissingTimeSeparator;
import duke.exception.DukeTaskOutOfRangeException;
import duke.exception.DukeInvalidFileContentException;
import duke.task.Task;
import duke.task.TaskManager;

import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Duke {

    protected static final String BORDER_DECORATION = "_____________________________________________________";
    protected static final Path FILE_PATH = Path.of("data/duke.txt");
    protected static boolean isLoadFile;

    public static void printGreeting() {
        String logo = " _______     __   __  \n"
                + "|   _   |[x]| | / / [x]  \n"
                + "|  | |  || ||  / /  | |\n"
                + "|  | |  || ||  <    | |\n"
                + "|__/ |__||_||_| |_| |_|\n";
        System.out.println("Hello from\n" + logo +"\n" + BORDER_DECORATION);
        System.out.println("Hello! I'm Niki\nWhat can I do for you?\n" + BORDER_DECORATION);
    }

    private static void printExit() {
        System.out.println(BORDER_DECORATION);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BORDER_DECORATION);
    }

    public static void performTodo(TaskManager taskManager, String userInput) {
        try {
            Task newTask = taskManager.addTask(userInput);
            saveDataAddToListOperation(taskManager, newTask);
        } catch (DukeEmptyDescriptionException e) {
            System.out.println("OOPS! The description of a todo cannot be empty!");
        } catch (DukeMaxTaskException e) {
            System.out.println("OOPS! You have reached the max number of tasks!");
        }
    }

    public static void performTaskWithTimeConstraints(TaskManager taskManager, String userInput, String stringSeparator, String taskType) {
        try {
            Task newTask = taskManager.addTaskWithTime(userInput, stringSeparator);
            saveDataAddToListOperation(taskManager, newTask);
        } catch (DukeEmptyDescriptionException e) {
            System.out.println("OOPS! The description of a " + taskType + " cannot be empty!");
        } catch (DukeMaxTaskException e) {
            System.out.println("OOPS! You have reached the max number of tasks!");
        } catch (DukeMissingTimeSeparator e) {
            System.out.println("OOPS! You did not include '" + stringSeparator + "' in your command!");
        }
    }

    public static void performMarking(TaskManager taskManager, String userInput, boolean isMarked, String markType) {
        try {
            int taskNumberMarked = taskManager.markTask(isMarked, userInput);
            saveDataChangeMarkStatusOperation(taskManager, taskNumberMarked, isMarked);
        } catch (DukeEmptyDescriptionException e) {
            System.out.println("OOPS! Please add the list number you want to " + markType +"!");
        } catch (NumberFormatException e) {
            System.out.println("OOPS! Specify a number for the list to " + markType +"!");
        } catch (DukeTaskOutOfRangeException e) {
            System.out.println("Task does not exist!");
        }
    }

    public static void executeCommand(TaskManager taskManager) {
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println(BORDER_DECORATION);
            String command = userInput.split(" ")[0];
            switch (command) {
            case "list":
                taskManager.printList();
                break;
            case "todo":
                performTodo(taskManager, userInput);
                break;
            case "deadline":
                performTaskWithTimeConstraints(taskManager, userInput, "/by ", command);
                break;
            case "event":
                performTaskWithTimeConstraints(taskManager, userInput, "/at ", command);
                break;
            case "unmark":
                performMarking(taskManager, userInput, false, command);
                break;
            case "mark":
                performMarking(taskManager, userInput, true, command);
                break;
            default:
                System.out.println("I'm sorry, but I don't know what that means :(");
            }
            System.out.println(BORDER_DECORATION);
            userInput = in.nextLine();
        }
    }

    public static void setupAndRunProgram() {
        TaskManager taskManager = new TaskManager();
        isLoadFile = true;
        try {
            loadDukeDataFile(taskManager);
            System.out.println("Initializing: Duke file loaded successfully...");
        } catch (IOException e) {
            System.out.println("File not found.");
        }
        isLoadFile = false;
        executeCommand(taskManager);
    }

    public static void loadDukeDataFile(TaskManager taskManager) throws IOException {
        checkFileExists();
        try {
            populateDukeFromDataFile(taskManager);
        } catch (IOException e) {
            System.out.println("Unable to populate data...");
        } catch (DukeInvalidFileContentException e) {
            System.out.println("Invalid file content...");
        }
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
                    performTodo(taskManager, command);
                    break;
                case "D":
                    performTaskWithTimeConstraints(taskManager, command, "/by ", "deadline");
                    break;
                case "E":
                    performTaskWithTimeConstraints(taskManager, command, "/at ", "event");
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
            performMarking(taskManager, buildMarkCommand, true, "mark");
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

    public static void checkFileExists() throws IOException {
        Path dataDirectory = FILE_PATH.getParent();
        if (!Files.isDirectory(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }

        if (Files.notExists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
        }
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

    public static void main(String[] args) {
        printGreeting();
        setupAndRunProgram();
        printExit();
    }
}
