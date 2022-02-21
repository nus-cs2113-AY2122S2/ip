import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

import com.sun.source.util.TaskListener;
import em.exception.EmptyTaskDescriptionException;
import em.exception.IllegalInputException;
import em.exception.InvalidContentException;
import em.exception.TaskOutOfRangeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;

public class Duke {
    public static final String LINESEPARATOR = "____________________________________________________________\n";
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static final Path DATABASE_FILEPATH = Path.of("database/database.txt");

    public static void displayLogo() {
        String logo = "                       ___\n"
                + "                      / ()\\\n"
                + "                    _|_____|_\n"
                + "                   | | === | |\n"
                + " _____  _      _   |_|  0  |_|\n"
                + "| ___| | \\    / |   ||  0  ||\n"
                + "| |__  |  \\  /  |   ||__*__||\n"
                + "|  __| |   \\/   |  |~ \\___/ ~| \n"
                + "| |___ | |\\__/| |  /=\\ /=\\ /=\\ \n"
                + "|_____||_|    |_|__[_]_[_]_[_]______________________________\n";
        String greetings = LINESEPARATOR
                + "Hello! I'm EM\n" + "What can I do for you?\n" + LINESEPARATOR;
        System.out.println(logo + greetings);
    }

    /*Display goodbye message*/
    public static void displayFarewell() {
        String farewell = LINESEPARATOR
                + "Bye. Hope to see you again soon!\n" + LINESEPARATOR;
        System.out.println(farewell);
    }

    public static void displayTaskList(ArrayList<Task> taskList) {
        System.out.println(LINESEPARATOR);
        System.out.println("Here are the tasks in your list:");
        if (taskList.size() == 0) {
            System.out.println("List is Empty!");
        }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println(LINESEPARATOR);
    }

    public static void addTask(String userInput, ArrayList<Task> taskList, Boolean isUserCommand) {
        String[] taskDescription = userInput.split(" ", 2);
        Task newTask = new ToDo(taskDescription[1]);
        taskList.add(newTask);

        if (isUserCommand) {
            System.out.println(LINESEPARATOR + "Got it. I've added this task:");
            System.out.println(taskList.get(taskList.size() - 1).toString());
            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n" + LINESEPARATOR);
            try {
                writeToFile(formulateDatabaseInput(taskDescription));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String formulateDatabaseInput(String[] taskDescription) {
        String databaseInput = null;
        switch (taskDescription[0]) {
        case "todo":
            databaseInput = "T," + "0" + ","+ taskDescription[1];
            break;
        case "deadline":
            databaseInput = "D," + "0" + "," + taskDescription[1] + "," + taskDescription[2];
            break;
        case "event":
            databaseInput = "E," + "0" + "," + taskDescription[1] + "," + taskDescription[2];
            break;
        }
        return databaseInput;
    }

    public static void addTaskAndTime(String userInput, ArrayList<Task> taskList, Boolean isUserCommand) {
        String[] arrayOfUserInput = userInput.split(" ", 2);
        arrayOfUserInput = userInput.split("/", 2);
        String taskLongDesc = arrayOfUserInput[0];
        String[] taskDescription = taskLongDesc.split(" ", 2);
        String timeWithTaskType = arrayOfUserInput[1];
        String[] timing = timeWithTaskType.split(" ", 2);
        Task newTask;
        if (timing[0].equals("by")) {
            newTask = new Deadline(taskDescription[1], timing[1]);
        } else {
            newTask = new Event(taskDescription[1], timing[1]);
        }
        taskList.add(newTask);
        if (isUserCommand) {
            System.out.println(LINESEPARATOR + "Got it. I've added this task:");
            System.out.println(taskList.get(taskList.size() - 1).toString());
            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n" + LINESEPARATOR);
            try {
                String[] databaseInput = new String[3];
                if (timing[0].equals("by")) {
                    databaseInput[0] = "deadline";
                } else {
                    databaseInput[0] = "event";
                }
                databaseInput[1] = taskDescription[1];
                databaseInput[2] = timing[1];
                writeToFile(formulateDatabaseInput(databaseInput));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isTaskValid(int taskNumber) throws TaskOutOfRangeException {
        if (taskNumber > taskList.size() || taskNumber <= 0) {
            throw new TaskOutOfRangeException();
        }
        return true;
    }

    public static String checkValidityOfInput(String userInput) throws EmptyTaskDescriptionException, IllegalInputException {
        String[] arrayOfUserInput = userInput.split(" ");
        if (arrayOfUserInput.length == 1 && arrayOfUserInput[0].equalsIgnoreCase("list")) {
            return userInput; //list
        } else if (arrayOfUserInput.length > 1 && arrayOfUserInput[0].equalsIgnoreCase("list")) {
            throw new IllegalInputException(); //list and more input
        } else if (arrayOfUserInput.length <= 1 && isCommandValid(arrayOfUserInput[0])) { //todo, deadline, event with no parameters
            throw new EmptyTaskDescriptionException(); //task with no description
        } else if ((arrayOfUserInput.length <= 1) || !isCommandValid(arrayOfUserInput[0])) {
            throw new IllegalInputException(); //not valid task action
        } else {
            return userInput;
        }
    }

    public static Boolean isCommandValid(String command) {
        if (command.equalsIgnoreCase("todo") || command.equalsIgnoreCase("event")
                || command.equalsIgnoreCase("deadline") || command.equalsIgnoreCase("delete")
                || command.equalsIgnoreCase("mark") || command.equalsIgnoreCase("unmark")) {
            return true;
        }
        return false;
    }

    private static void populateFileContents(Path filePath) throws FileNotFoundException, InvalidContentException {
        List<String> fileContentLines = null;
        try {
            fileContentLines = Files.readAllLines(DATABASE_FILEPATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String lines:fileContentLines) {
            String userInput = null;
            String[] contentsInALine = lines.split(",", -1);
            if (contentsInALine.length < 1) {
                throw new InvalidContentException();
            }
            switch (contentsInALine[0].trim()) {
            case "T":
                userInput = "todo " + contentsInALine[2].trim();
                addTask(userInput, taskList, false);
                break;
            case "D":
                userInput = "deadline " + contentsInALine[2].trim() + " " + "/by " + contentsInALine[3].trim();
                addTaskAndTime(userInput, taskList, false);
                break;
            case "E":
                userInput = "event " + contentsInALine[2].trim() + " " + "/at " + contentsInALine[3].trim();
                addTaskAndTime(userInput, taskList, false);
                break;
            default:
                break;
            }
            if (contentsInALine[1].trim().equalsIgnoreCase("1")) {
                taskList.get(taskList.size() - 1).markAsDone(taskList.size(), taskList, false);
            }
        }
    }

    public static void writeToFile(String textToAppend) throws IOException {
        String modifiedContent = System.lineSeparator() + textToAppend;
        Files.write(DATABASE_FILEPATH, modifiedContent.getBytes(), StandardOpenOption.APPEND);
        removeSpaces();
    }

    public static void removeSpaces() throws IOException {
        String content = "";
        List<String> fileContentLines = Files.readAllLines(DATABASE_FILEPATH);
        for (String lines:fileContentLines) {
            lines = lines.trim();
            if(lines.length() > 0) {
                content += lines + System.lineSeparator();
            }
            Files.write(DATABASE_FILEPATH, content.getBytes());
        }
    }

    public static void modifyDatabase(int taskNumber, Boolean isMark, Boolean isDelete) {
        try {
            removeSpaces();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String oldContent = "";
        String newLine = "";
        try {
            if (isDelete) {
                newLine = "";
            } else {
                newLine = modifyContent(taskNumber, isMark);
            }
            List<String> fileContentLines = Files.readAllLines(DATABASE_FILEPATH);
            int lineNumber = 1;
            for (String lines:fileContentLines) {
                if (taskNumber == lineNumber) {
                    oldContent += (newLine + "\n");
                } else {
                    oldContent += (lines + "\n");
                }
                lineNumber += 1;
            }
            Files.write(DATABASE_FILEPATH, oldContent.getBytes());
            removeSpaces();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String modifyContent(int taskNumber, Boolean isMark) {
        String newLine = "";
        try {
            String oldLine = Files.readAllLines(DATABASE_FILEPATH).get(taskNumber - 1);
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

    public static void checkFileExists() throws IOException {
        Path dataDirectory = DATABASE_FILEPATH.getParent();
        if (!Files.isDirectory(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }

        if (Files.notExists(DATABASE_FILEPATH)) {
            Files.createFile(DATABASE_FILEPATH);
        }
    }

    public static void processAction() {
        try {
            checkFileExists();
        } catch (IOException e) {
            System.out.println("failedddd");
            e.printStackTrace();
        }
        try {
            populateFileContents(DATABASE_FILEPATH);
        } catch (FileNotFoundException | InvalidContentException e) {
            System.out.println("File not Found in here");
        }
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while (!userInput.equalsIgnoreCase("Bye")) {
            try {
                String validUserInput = checkValidityOfInput(userInput);
                String action = userInput.split(" ")[0];
                switch (action.toLowerCase()) {
                case "list":
                    displayTaskList(taskList);
                    break;
                case "mark":
                    int markTaskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    if (isTaskValid(markTaskNumber)) {
                        taskList.get(markTaskNumber - 1).markAsDone(markTaskNumber, taskList, true);
                        modifyDatabase(markTaskNumber, true, false);
                    }
                    break;
                case "unmark":
                    int unmarkTaskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    if (isTaskValid(unmarkTaskNumber)) {
                        taskList.get(unmarkTaskNumber - 1).markAsUndone(unmarkTaskNumber, taskList, true);
                        modifyDatabase(unmarkTaskNumber, false, false);
                    }
                    break;
                case "todo":
                    addTask(userInput, taskList, true);
                    break;
                case "event":
                case "deadline":
                    addTaskAndTime(userInput, taskList, true);
                    break;
                case "delete":
                    int deleteTaskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    if (isTaskValid(deleteTaskNumber)) {
                        taskList.get(deleteTaskNumber - 1).deleteTask(deleteTaskNumber, taskList);
                        modifyDatabase(deleteTaskNumber, false, true);
                    }
                    break;
                default:
                    System.out.println("Invalid");
                }
            } catch (TaskOutOfRangeException e) {
                System.out.println(LINESEPARATOR + "The task you are looking for is not available.");
                System.out.print("☹ OOPS!!! Please enter a valid task number.\n" + LINESEPARATOR);
            } catch (IllegalInputException e) {
                System.out.print(LINESEPARATOR + "☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n" + LINESEPARATOR);
            } catch (EmptyTaskDescriptionException e) {
                System.out.print(LINESEPARATOR + "☹ OOPS!!! The task description cannot be empty.\n" + LINESEPARATOR);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(LINESEPARATOR + "Did not include time or description.\n" + LINESEPARATOR);
            } catch (NumberFormatException e) {
                System.out.print(LINESEPARATOR + "The task number entered is not a number\n" + LINESEPARATOR);
            }
            userInput = in.nextLine();
        }
    }

    public static void main(String[] args) {
        displayLogo();
        processAction();
        displayFarewell();
    }
}