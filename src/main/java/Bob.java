import java.util.Locale;
import java.util.Scanner;

import util.Task;
import util.Deadlines;
import util.ToDos;
import util.Events;

public class Bob {

    /**
     * Prints a horizontal border for actions performed.
     */
    public static void printBorder() {
        String border = "\t____________________________________________________________";
        System.out.println(border);
    }

    /**
     * Prints the initial greetings when the program starts.
     */
    public static void greetings() {
        printBorder();
        String logo = "\t.______     ______   .______   \n"
                + "\t|   _  \\   /  __  \\  |   _  \\  \n"
                + "\t|  |_)  | |  |  |  | |  |_)  | \n"
                + "\t|   _  <  |  |  |  | |   _  <  \n"
                + "\t|  |_)  | |  `--'  | |  |_)  | \n"
                + "\t|______/   \\______/  |______/  \n\n"
                + "\tHello! I'm Bob\n"
                + "\tWhat can I do for you?";
        System.out.println(logo);
        printBorder();
    }

    /**
     * Prints the exit message.
     */
    public static void goodBye() {
        printBorder();
        System.out.println("\tSee next time. /|\\(◦.◦)/|\\");
        printBorder();
    }

    /**
     * Displays the current tasks and their statuses.
     *
     * @param list a Task class list.
     */
    public static void displayList(Task[] list) {
        printBorder();

        int count = Task.getCount();
        if (count > 0) {
            System.out.println("\tTask list:");
            for (int i = 0; i < count; i++) {
                System.out.print("\t" + (i + 1) + ".");
                System.out.println(list[i]);
            }
        }
        else {
            System.out.println("Currently no tasks");
        }
        printBorder();
    }

    /**
     * Updates the completion status of an indicated task.
     *
     * @param list       a Task class list.
     * @param command    the command containing the class id to be updated.
     * @param doneStatus the status to be updated to.
     */
    public static void updateStatus(Task[] list, String command, boolean doneStatus) {
        printBorder();
        if (command.split(" ").length != 2) { // Invalid input
            printError("\tInvalid number of arguments.");
            return;
        }
        int id = Integer.parseInt(command.split(" ")[1]);
        if (id > Task.getCount()) { // Invalid input
            printError("\tInvalid task id detected.");
            return;
        }
        Task target = list[id - 1];
        target.setDone(doneStatus);

        if (doneStatus) {
            System.out.println("\tThe following task has been checked off:");
        }
        else {
            System.out.println("\tThe following task has yet to be completed:");
        }
        System.out.println("\t" + target);
        printBorder();
    }

    /**
     * Appends a Task to the task list.
     *
     * @param list   a Task class list.
     * @param object a Task class object.
     */
    public static void addTaskToList(Task[] list, Task object) {
        int index = Task.getCount() - 1;
        list[index] = object;

        System.out.println("\t" + object);
        System.out.println("\tThere are " + Task.getCount() + " tasks now");
    }

    /**
     * Creates a new undated task and appends it to the task list.
     *
     * @param list    a Task class list.
     * @param command the details of the new undated task to be created.
     */
    public static void addUndatedTask(Task[] list, String command) {
        printBorder();

        String[] commandSplit = command.split(" ", 2);
        Task temp = new ToDos(commandSplit[1]);

        addTaskToList(list, temp);
        printBorder();
    }

    /**
     * Creates a new dated task and appends it to the task list.
     *
     * @param list    a Task class list.
     * @param command the details of the new dated task to be created.
     */
    public static void addDatedTask(Task[] list, String command) {
        printBorder();
        Task temp = null;
        boolean isError = false;

        String[] commandSplit = command.split(" ", 2);

        switch (commandSplit[0]) {
        case "deadline":
            if (commandSplit[1].contains(" /by ")) {
                String[] descAndDeadline = commandSplit[1].split(" /by ", 2);
                temp = new Deadlines(descAndDeadline[0], descAndDeadline[1]);
            }
            else { // Incorrect input
                isError = true;
            }
            break;
        case "event":
            if (commandSplit[1].contains(" /at ")) {
                String[] descAndPeriod = commandSplit[1].split(" /at ", 2);
                temp = new Events(descAndPeriod[0], descAndPeriod[1]);
            }
            else { // Incorrect input
                isError = true;
            }
            break;
        default:
            isError = true;
        }

        if (isError) {
            printError("Usage: [deadline,event] <task> [/by,/at] <date>");
            return;
        }

        addTaskToList(list, temp);
        printBorder();
    }

    /**
     * Prints the stated error message at the end of command execution.
     *
     * @param message an Error message.
     */
    public static void printError(String message) {
        System.out.println("\t" + message);
        printBorder();
    }

    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];

        greetings();
        do {
            System.out.println();
            command = in.nextLine();

            switch (command.split(" ")[0]) {
            case "list":
                displayList(list);
                continue;
            case "mark":
                updateStatus(list, command, true);
                continue;
            case "unmark":
                updateStatus(list, command, false);
                continue;
            case "todo":
                addUndatedTask(list, command);
                break;
            case "deadline":
            case "event":
                addDatedTask(list, command);
                continue;
            case "bye":
                break;
            default:
                System.out.println("Sorry, I do not understand.");
            }
        } while (command.split(" ")[0].compareTo("bye") != 0);

        goodBye();
    }
}
