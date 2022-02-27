package duke;

import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    protected static final String BORDER_DECORATION = "_____________________________________________________";
    private static Parser parser = new Parser();

    public Ui() {
    }

    public static void printGreeting() {
        String logo = " _______     __   __  \n"
                + "|   _   |[x]| | / / [x]  \n"
                + "|  | |  || ||  / /  | |\n"
                + "|  | |  || ||  <    | |\n"
                + "|__/ |__||_||_| |_| |_|\n";
        System.out.println("Hello from\n" + logo +"\n" + BORDER_DECORATION);
        System.out.println("Hello! I'm Niki\nWhat can I do for you?\n" + BORDER_DECORATION);
    }

    public static void printExit() {
        System.out.println(BORDER_DECORATION);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BORDER_DECORATION);
    }

    public static void executeCommand(TaskList taskManager) {
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
                parser.performTodo(taskManager, userInput);
                break;
            case "deadline":
                parser.performTaskWithTimeConstraints(taskManager, userInput, "/by ", command);
                break;
            case "event":
                parser.performTaskWithTimeConstraints(taskManager, userInput, "/at ", command);
                break;
            case "unmark":
                parser.performMarking(taskManager, userInput, false, command);
                break;
            case "mark":
                parser.performMarking(taskManager, userInput, true, command);
                break;
            case "delete":
                parser.performDeletion(taskManager, userInput);
                break;
            default:
                System.out.println("I'm sorry, but I don't know what that means :(");
            }
            System.out.println(BORDER_DECORATION);
            userInput = in.nextLine();
        }
    }
}
