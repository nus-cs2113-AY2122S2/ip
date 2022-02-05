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

    public static final String MESSAGE_INVALID_COMMAND = "Sorry, I do not understand.";
    public static final String MESSAGE_INVALID_ARGC = "Invalid number of arguments. ( °□°)";
    public static final String MESSAGE_INVALID_ID = "Invalid task id. ┬┴┬┴┤ω・)";
    public static final String MESSAGE_DEADLINE_USAGE = "Usage: deadline <task> /by <deadline>";
    public static final String MESSAGE_EVENT_USAGE = "Usage: event <task> /at <date,time>";
    public static final String MESSAGE_TASK_LIMIT_REACHED = "No more tasks can be created :(";

    // Delimiters
    public static final String DELIMIT_COMMAND = " ";
    public static final String DELIMIT_DEADLINE = " /by ";
    public static final String DELIMIT_EVENT = " /at ";

    // Magic numbers
    public static final int MAX_TASK = 100;
    public static final int TOKEN_LENGTH = 2;

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
     * Takes in a command delimited by a space and parses it into 2 trimmed tokens.
     *
     * @param command The command to be parsed.
     * @return A list containing the main command then its details.
     */
    public static String[] parseCommand(String command, String delimiter) {
        String[] commandToken = command.split(delimiter, TOKEN_LENGTH);
        commandToken[0] = commandToken[0].trim();
        if (commandToken.length == TOKEN_LENGTH) {
            commandToken[1] = commandToken[1].trim();
            return commandToken;
        } else {
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
     * Prints the formatted actual id of a task.
     *
     * @param id the id to be formatted.
     */
    public static void printListId(int id) {
        String formatId = String.format("%-4s", (id + 1) + ".");
        printTab(formatId);
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
                printListId(i);
                System.out.println(list[i]);
            }
        } else {
            printlnTab(MESSAGE_NO_TASKS);
        }
        printBorder();
    }

    /**
     * Updates the completion status of an indicated task.
     *
     * @param list       a Task class list.
     * @param command    the command containing the task id to be updated.
     * @param doneStatus the status to be updated to.
     */
    public static void updateStatus(Task[] list, String command, boolean doneStatus) {
        printBorder();
        String[] commandToken = parseCommand(command, DELIMIT_COMMAND);

        if (commandToken[1] != null) {
            // NumberFormatException
            int id = Integer.parseInt(commandToken[1]);
            if (id > Task.getCount() || id <= 0) {
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
        } else {
            printError(MESSAGE_INVALID_ARGC);
        }
    }

    /**
     * Appends a Task to the task list.
     *
     * @param list a Task class list.
     * @param task the Task to be added.
     */
    public static void addTaskToList(Task[] list, Task task) {
        int index = Task.getCount() - 1;
        list[index] = task;
        System.out.println("\t" + task);
        printlnTab(MESSAGE_TASK_COUNT + Task.getCount());
    }

    /**
     * Creates a new undated task and appends it to the task list.
     *
     * @param list        a Task class list.
     * @param taskDetails the details of the new undated task to be created.
     */
    public static void addToDosTask(Task[] list, String taskDetails) {
        Task task = new ToDos(taskDetails);
        addTaskToList(list, task);
        printBorder();
    }

    /**
     * Creates a new dated deadline task and appends it to the task list.
     *
     * @param list        a Task class list.
     * @param taskDetails the details of the new dated task to be created.
     */
    public static void addDeadlineTask(Task[] list, String taskDetails) {
        String[] tokenDetails = parseCommand(taskDetails, DELIMIT_DEADLINE);

        // check that the deadline is not empty
        if (tokenDetails[1] != null) {
            Task task = new Deadlines(tokenDetails[0], tokenDetails[1]);
            addTaskToList(list, task);
            printBorder();
        } else {
            printError(MESSAGE_DEADLINE_USAGE);
        }
    }

    /**
     * Creates a new dated event task and appends it to the task list.
     *
     * @param list        a Task class list.
     * @param taskDetails the details of the new dated task to be created.
     */
    public static void addEventTask(Task[] list, String taskDetails) {
        String[] tokenDetails = parseCommand(taskDetails, DELIMIT_EVENT);

        // check that the event period is not empty
        if (tokenDetails[1] != null) {
            Task task = new Events(tokenDetails[0], tokenDetails[1]);
            addTaskToList(list, task);
            printBorder();
        } else {
            printError(MESSAGE_EVENT_USAGE);
        }
    }

    /**
     * Validates the details of a task before creating and adding it into the list.
     *
     * @param list    a Task class list.
     * @param command the command containing the type of task and its details.
     */
    public static void addNewValidTask(Task[] list, String command) {
        printBorder();
        if (Task.getCount() < MAX_TASK) {
            String[] commandToken = parseCommand(command, DELIMIT_COMMAND);

            // check that command details are not empty
            if (commandToken[1] != null) {
                switch (commandToken[0]) {
                case "todo":
                    addToDosTask(list, commandToken[1]);
                    break;
                case "deadline":
                    addDeadlineTask(list, commandToken[1]);
                    break;
                case "event":
                    addEventTask(list, commandToken[1]);
                    break;
                }
            } else {
                printError(MESSAGE_INVALID_ARGC);
            }
        } else {
            printError(MESSAGE_TASK_LIMIT_REACHED);
        }
    }

    /**
     * Listen and execute user commands until exit command is issued.
     */
    public static void listenAndExecuteCommands() {
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[MAX_TASK];

        String command;
        do {
            System.out.println();
            command = in.nextLine().trim();

            // check for valid command
            switch (parseCommand(command, DELIMIT_COMMAND)[0]) {
            case "list":
                displayList(list);
                break;
            case "mark":
                updateStatus(list, command, true);
                break;
            case "unmark":
                updateStatus(list, command, false);
                break;
            case "todo":
            case "deadline":
            case "event":
                addNewValidTask(list, command);
                break;
            case "bye":
                break;
            default:
                printlnTab(MESSAGE_INVALID_COMMAND);
            }
        } while (!parseCommand(command, DELIMIT_COMMAND)[0].equals("bye"));
    }

    public static void main(String[] args) {
        greetings();
        listenAndExecuteCommands();
        goodBye();
    }
}
