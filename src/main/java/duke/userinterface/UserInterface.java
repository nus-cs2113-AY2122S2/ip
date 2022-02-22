package duke.userinterface;

import duke.taskmanagement.TaskManager;

import java.util.Scanner;

public class UserInterface {
    public void run(TaskManager tasks) {
        Scanner in = new Scanner(System.in);
        String userInput = "";
        while (!userInput.equals("bye")) {
            printLine();
            userInput = in.nextLine().trim();
            String command = getCommand(userInput);
            switch (command) {
            case "list":
                tasks.listTasks();
                break;
            case "todo":
                tasks.addTodo(userInput);
                break;
            case "deadline":
                tasks.addDeadline(userInput);
                break;
            case "event":
                tasks.addEvent(userInput);
                break;
            case "mark":
            case "unmark":
                tasks.markOrUnmarkTask(userInput);
                break;
            case "delete":
                tasks.deleteTask(userInput);
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

    private void printLine() {
        System.out.println("------------------------------");
    }

    private String getCommand(String userInput) {
        String[] words = userInput.split(" ");
        String command = words[0];
        return command;
    }

    private static void printHelpMessage() {
        System.out.println("Sorry I don't know what you mean.");
        System.out.println("There are currently eight keyword commands: ");
        System.out.println("1. list, 2. todo, 3. deadline, 4.event, 5. mark, 6. unmark, 7. delete, 8. bye");
    }
}
