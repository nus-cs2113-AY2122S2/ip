import java.util.Scanner;

import util.Task;
import util.Deadlines;
import util.ToDos;
import util.Events;

public class Bob {

    // Miscellaneous Text
    public static final String BORDER = "____________________________________________________________";
    public static final String LOGO = ".______     ______   .______   \n"
            + "\t|   _  \\   /  __  \\  |   _  \\  \n"
            + "\t|  |_)  | |  |  |  | |  |_)  | \n"
            + "\t|   _  <  |  |  |  | |   _  <  \n"
            + "\t|  |_)  | |  `--'  | |  |_)  | \n"
            + "\t|______/   \\______/  |______/  \n";

    // Messages
    public static final String INTRO_GREETING = "Hello! I'm Bob ʘ‿ʘ";
    public static final String INTRO_QUESTION = "What can I do for you today?";
    public static final String OUTRO_GOODBYE = "See you next time. /|\\(◦.◦)/|\\";

    public static final String MESSAGE_TASK_LIST = "Task list:";
    public static final String MESSAGE_NO_TASKS = "There are currently no tasks. ¯\\_(ツ)_/¯";
    public static final String MESSAGE_TASK_MARKED = "The following task has been checked off:";
    public static final String MESSAGE_TASK_UNMARKED = "The following task has yet to be completed:";
    public static final String MESSAGE_TASK_COUNT = "The number of tasks amounts to: ";

    public static final String MESSAGE_INVALID_ARGC = "Invalid number of arguments. ( °□°)";
    public static final String MESSAGE_INVALID_ID = "Invalid task id. ┬┴┬┴┤ω・)";
    public static final String MESSAGE_DEADLINE_USAGE = "Usage: deadline <task> /by <deadline>";
    public static final String MESSAGE_EVENT_USAGE = "Usage: event <task> /at <date,time>";

    // Delimiters
    public static final String DELIMIT_COMMAND = " ";
    public static final String DELIMIT_DEADLINE = " /by ";
    public static final String DELIMIT_EVENT = " /at ";

    // Magic numbers
    public static final int MAX_TASK = 100;

    /**
     * Prints the message prepended by a tab.
     *
     * @param message Text to be printed.
     */
    public static void printTab(String message) {
        System.out.print("\t" + message);
    }

    /**
     * Prints the message prepended by a tab and appended by a new line.
     *
     * @param message Text to be printed.
     */
    public static void printlnTab(String message) {
        System.out.println("\t" + message);
    }

    /**
     * Prints a horizontal border for actions performed.
     */
    public static void printBorder() {
        printlnTab(BORDER);
    }

    /**
     * Prints the stated error message at the end of command execution.
     *
     * @param message an Error message.
     */
    public static void printError(String message) {
        printlnTab(message);
        printBorder();
    }

    /**
     * Takes in a command delimited by a space and parses it into 2 tokens.
     *
     * @param command The command to be parsed.
     * @return A list containing the main command then its details.
     */
    public static String[] parseCommand(String command, String delimiter) {
        String[] commandToken = command.split(delimiter, 2);
        if (commandToken.length == 2) {
            return commandToken;
        }
        else {
            return new String[]{commandToken[0], null};
        }
    }

    /**
     * Prints the initial greetings when the program starts.
     */
    public static void greetings() {
        printBorder();
        printlnTab(LOGO);
        printlnTab(INTRO_GREETING);
        printlnTab(INTRO_QUESTION);
        printBorder();
    }

    /**
     * Prints the exit message.
     */
    public static void goodBye() {
        printBorder();
        printlnTab(OUTRO_GOODBYE);
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
            printlnTab(MESSAGE_TASK_LIST);
            for (int i = 0; i < count; i++) {
                printTab((i + 1) + ".");
                System.out.println(list[i]);
            }
        }
        else {
            printlnTab(MESSAGE_NO_TASKS);
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
        String[] commandToken = parseCommand(command, DELIMIT_COMMAND);

        if (commandToken[1] != null) {
            int id = Integer.parseInt(commandToken[1]);
            if (id > Task.getCount()) {
                printError(MESSAGE_INVALID_ID);
                return;
            }

            Task target = list[id - 1];
            target.setDone(doneStatus);

            if (doneStatus) {
                printlnTab(MESSAGE_TASK_MARKED);
            } else {
                printlnTab(MESSAGE_TASK_UNMARKED);
            }
            System.out.println("\t" + target);
            printBorder();
        }
        else {
            printError(MESSAGE_INVALID_ARGC);
        }
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
        printlnTab(MESSAGE_TASK_COUNT + Task.getCount());
    }

    /**
     * Creates a new undated task and appends it to the task list.
     *
     * @param list    a Task class list.
     * @param command the details of the new undated task to be created.
     */
    public static void addToDosTask(Task[] list, String command) {
        printBorder();
        String[] commandToken = parseCommand(command, DELIMIT_COMMAND);

        if (commandToken[1] != null) {
            Task temp = new ToDos(commandToken[1]);

            addTaskToList(list, temp);
            printBorder();
        }
        else {
            printError(MESSAGE_INVALID_ARGC);
        }
    }

    /**
     * Creates a new dated deadline task and appends it to the task list.
     *
     * @param list    a Task class list.
     * @param command the details of the new dated task to be created.
     */
    public static void addDeadlineTask(Task[] list, String command) {
        printBorder();
        String[] commandToken = parseCommand(command, DELIMIT_COMMAND);

        if (commandToken[1] != null) {
            String[] taskDetails = parseCommand(commandToken[1], DELIMIT_DEADLINE);

            if (taskDetails[1] != null) {
                Task temp = new Deadlines(taskDetails[0], taskDetails[1]);

                addTaskToList(list, temp);
                printBorder();
            }
            else {
                printError(MESSAGE_DEADLINE_USAGE);
            }
        }
        else {
            printError(MESSAGE_INVALID_ARGC);
        }
    }

    /**
     * Creates a new dated event task and appends it to the task list.
     *
     * @param list    a Task class list.
     * @param command the details of the new dated task to be created.
     */
    public static void addEventTask(Task[] list, String command) {
        printBorder();
        String[] commandToken = parseCommand(command, DELIMIT_COMMAND);

        if (commandToken[1] != null) {
            String[] taskDetails = parseCommand(commandToken[1], DELIMIT_EVENT);

            if (taskDetails[1] != null) {
                Task temp = new Events(taskDetails[0], taskDetails[1]);

                addTaskToList(list, temp);
                printBorder();
            }
            else {
                printError(MESSAGE_EVENT_USAGE);
            }
        }
        else {
            printError(MESSAGE_INVALID_ARGC);
        }
    }

    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[MAX_TASK];

        greetings();
        do {
            System.out.println();
            command = in.nextLine();

            switch (parseCommand(command, DELIMIT_COMMAND)[0]) {
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
                addToDosTask(list, command);
                break;
            case "deadline":
                addDeadlineTask(list, command);
                continue;
            case "event":
                addEventTask(list, command);
                continue;
            case "bye":
                break;
            default:
                printlnTab("Sorry, I do not understand.");
            }
        } while (parseCommand(command, DELIMIT_COMMAND)[0].compareTo("bye") != 0);

        goodBye();
    }
}
