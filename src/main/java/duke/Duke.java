package duke;

import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeMissingTimeSeparator;
import duke.exception.DukeTaskOutOfRangeException;
import duke.task.TaskManager;

import java.util.Scanner;

public class Duke {

    protected static final String BORDER_DECORATION = "_____________________________________________________";

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
            taskManager.addTask(userInput);
        } catch (DukeEmptyDescriptionException e) {
            System.out.println("OOPS! The description of a todo cannot be empty!");
        }
    }

    public static void performTaskWithTimeConstraints(TaskManager taskManager, String userInput, String stringSeparator, String taskType) {
        try {
            taskManager.addTaskWithTime(userInput, stringSeparator);
        } catch (DukeEmptyDescriptionException e) {
            System.out.println("OOPS! The description of a " + taskType + " cannot be empty!");
        } catch (DukeMissingTimeSeparator e) {
            System.out.println("OOPS! You did not include '" + stringSeparator + "' in your command!");
        }
    }

    public static void performMarking(TaskManager taskManager, String userInput, boolean isMarked, String markType) {
        try {
            taskManager.markTask(isMarked, userInput);
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
        executeCommand(taskManager);
    }

    public static void main(String[] args) {
        printGreeting();
        setupAndRunProgram();
        printExit();
    }
}
