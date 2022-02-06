package duke;

import duke.taskmanager.TaskManager;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput = "";
        TaskManager taskList = new TaskManager();
        printGreeting();
        while (!userInput.equals("bye")) {
            printLine();
            userInput = in.nextLine().trim();
            String[] words = userInput.split(" ");
            String command = words[0];
            switch (command) {
            case "list":
                taskList.listTasks();
                break;
            case "todo":
                taskList.addTodo(userInput);
                break;
            case "deadline":
                taskList.addDeadline(userInput);
                break;
            case "event":
                taskList.addEvent(userInput);
                break;
            case "mark":
            case "unmark":
                taskList.markOrUnmarkTask(userInput);
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "":
                System.out.println("Well, I'll wait for a command..");
                break;
            default:
                printHelpMessage();
                break;
            }
        }
    }

    private static void printLine() {
        System.out.println("------------------------------");
    }

    private static void printGreeting() {
        printLine();
        System.out.println("Hello! I'm duke.Duke.");
        System.out.println("What can I do for you?");
    }

    private static void printHelpMessage() {
        System.out.println("Sorry I don't know what you mean.");
        System.out.println("There are currently six keyword commands: ");
        System.out.println("1. list, 2. todo, 3. deadline, 4. mark, 5. unmark, 6. bye");
    }
}
