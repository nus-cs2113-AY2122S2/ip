import java.util.Scanner;

public class Duke {

    // Misc text elements
    public static final String HORIZONTAL_SEPARATOR = "------------------------------------------------------------";
    public static final String INPUT_PROMPT = "> ";
    public static final String LS = System.lineSeparator();

    // Messages
    public static final String MESSAGE_WELCOME = "Hi, I'm Robit!" + LS + "What would you like me to do?";
    public static final String MESSAGE_SHOW_TASKS = "Here are your tasks:";
    public static final String MESSAGE_NO_TASKS = "You don't have any tasks!";
    public static final String MESSAGE_INCORRECT_COMMAND_FORMAT = "Incorrect command format for %s." + LS
                                                                    + "Usage: \"%s\"";
    public static final String MESSAGE_TODO_ADDED = "Todo successfully added:" + LS + "\t%s";
    public static final String MESSAGE_DEADLINE_ADDED = "Deadline successfully added:" + LS + "\t%s";
    public static final String MESSAGE_EVENT_ADDED = "Event successfully added:" + LS + "\t%s";
    public static final String MESSAGE_UNKNOWN_COMMAND = "I don't understand that command...";
    public static final String MESSAGE_TOO_MANY_TASKS = "I can't remember that many tasks...";
    public static final String MESSAGE_NO_SUCH_INDEX = "There's no task with that index...";
    public static final String MESSAGE_TASK_MARKED = "I've marked this task as done. Yay!" + LS + "%d) %s";
    public static final String MESSAGE_TASK_UNMARKED = "I've unmarked this task." + LS + "%d) %s";
    public static final String MESSAGE_TASK_ALREADY_MARKED = "That task is already marked.";
    public static final String MESSAGE_TASK_ALREADY_UNMARKED = "That task is already unmarked.";
    public static final String MESSAGE_GOODBYE = "Goodbye!";

    // Commands
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";

    // Argument separators
    public static final String DEADLINE_SEPARATOR = " /by ";
    public static final String EVENT_SEPARATOR = " /at ";

    // Usage examples
    public static final String USAGE_BYE = COMMAND_BYE;
    public static final String USAGE_LIST = COMMAND_LIST;
    public static final String USAGE_TODO = COMMAND_TODO + " <your task>";
    public static final String USAGE_DEADLINE = COMMAND_DEADLINE + " <your task>"
                                                + DEADLINE_SEPARATOR + "<task deadline>";
    public static final String USAGE_EVENT = COMMAND_EVENT + " <your event>" + EVENT_SEPARATOR + "<time>";
    public static final String USAGE_MARK = COMMAND_MARK + " <task index>";
    public static final String USAGE_UNMARK = COMMAND_UNMARK + " <task index>";

    // Program logic stuff
    private static final Scanner in = new Scanner(System.in);

    public static final int MAX_TASKS = 100;
    private static Task[] savedTasks = new Task[MAX_TASKS];
    private static int numSavedTasks = 0;

    private static boolean isExitRequested = false;

    /**
     * Main entry point of the program.
     */
    public static void main(String[] args) {
        printMessage(MESSAGE_WELCOME);
        while (!isExitRequested) {
            String[] input = getUserInput();
            String response = parseCommand(input);
            printMessage(response);
        }
    }

    /**
     * Prints the specified message, followed by a horizontal separator on the next line.
     * @param message the message to be printed
     */
    private static void printMessage(String message) {
        System.out.println(message);
        System.out.println(HORIZONTAL_SEPARATOR);
    }

    /**
     * Reads user input and separates the command being invoked from its arguments.
     * @return a String array of the form {command, args}
     */
    private static String[] getUserInput() {
        System.out.print(INPUT_PROMPT);
        String userInput = in.nextLine();
        System.out.println(HORIZONTAL_SEPARATOR);
        String[] userInputTokenized = userInput.trim().split(" ", 2);
        if (userInputTokenized.length == 2) {
            return userInputTokenized;
        }
        return new String[] {userInputTokenized[0], ""};
    }

    /**
     * Generates an error message for an invalid command.
     * @param usageExample string showing the correct command format
     * @return generated error message
     */
    private static String invalidCommandError(String command, String usageExample) {
        return String.format(MESSAGE_INCORRECT_COMMAND_FORMAT, command, usageExample);
    }

    /**
     * Attempts to add the specified task to the task list.
     * @param t task to be added
     * @param successMessage message to be displayed on success
     */
    private static String addTask(Task t, String successMessage) {
        if (numSavedTasks < MAX_TASKS) {
            savedTasks[numSavedTasks] = t;
            numSavedTasks++;
            return String.format(successMessage, t);
        }
        return MESSAGE_TOO_MANY_TASKS;
    }

    /**
     * Invokes the correct command function based on the command string inputted by the user.
     */
    private static String parseCommand(String[] input) {
        final String command = input[0];
        final String args = input[1];
        switch (command) {
        case COMMAND_BYE:
            return byeCommand(args);
        case COMMAND_LIST:
            return listCommand(args);
        case COMMAND_TODO:
            return todoCommand(args);
        case COMMAND_DEADLINE:
            return deadlineCommand(args);
        case COMMAND_EVENT:
            return eventCommand(args);
        case COMMAND_MARK:
            return markCommand(args);
        case COMMAND_UNMARK:
            return unmarkCommand(args);
        default:
            return MESSAGE_UNKNOWN_COMMAND;
        }
    }

    /**
     * Request to exit the program.
     */
    private static String byeCommand(String args) {
        if (args.equals("")) {
            isExitRequested = true;
            return MESSAGE_GOODBYE;
        }
        return invalidCommandError(COMMAND_BYE, USAGE_BYE);
    }

    /**
     * Lists all saved tasks.
     */
    private static String listCommand(String args) {
        if (args.equals("")) {
            if (numSavedTasks == 0) {
                return MESSAGE_NO_TASKS;
            }
            String result = MESSAGE_SHOW_TASKS;
            for (int i = 0; i < numSavedTasks; i++) {
                result += LS + (i+1) + ") " + savedTasks[i];
            }
            return result;
        }
        return invalidCommandError(COMMAND_LIST, USAGE_LIST);
    }

    /**
     * Adds a new to-do task.
     */
    private static String todoCommand(String args) {
        if (!args.equals("")) {
            Todo newTodo = new Todo(args.trim());
            return addTask(newTodo, MESSAGE_TODO_ADDED);
        }
        return invalidCommandError(COMMAND_TODO, USAGE_TODO);
    }

    /**
     * Adds a new deadline task. Parsing of the deadline separator occurs in the function body.
     */
    private static String deadlineCommand(String args) {
        if (!args.equals("")) {
            String[] parsedArgs = args.trim().split(DEADLINE_SEPARATOR, 2);
            if (parsedArgs.length == 2) {
                final String taskDescription = parsedArgs[0].trim();
                final String taskDeadline = parsedArgs[1].trim();
                Deadline newDeadline = new Deadline(taskDescription, taskDeadline);
                return addTask(newDeadline, MESSAGE_DEADLINE_ADDED);
            }
            return invalidCommandError(COMMAND_DEADLINE, USAGE_DEADLINE);
        }
        return invalidCommandError(COMMAND_DEADLINE, USAGE_DEADLINE);
    }

    /**
     * Adds a new event task. Parsing of the event separator occurs in the function body.
     */
    private static String eventCommand(String args) {
        if (!args.equals("")) {
            String[] parsedArgs = args.trim().split(EVENT_SEPARATOR, 2);
            if (parsedArgs.length == 2) {
                final String eventDescription = parsedArgs[0].trim();
                final String eventTime = parsedArgs[1].trim();
                Event newEvent = new Event(eventDescription, eventTime);
                return addTask(newEvent, MESSAGE_EVENT_ADDED);
            }
            return invalidCommandError(COMMAND_EVENT, USAGE_EVENT);
        }
        return invalidCommandError(COMMAND_EVENT, USAGE_EVENT);
    }

    /**
     * Marks the specified task as completed.
     */
    private static String markCommand(String args) {
        try {
            int index = Integer.parseInt(args);
            if (index > 0 && index <= numSavedTasks) {
                if (savedTasks[index - 1].getIsDone()) {
                    return MESSAGE_TASK_ALREADY_MARKED;
                } else {
                    savedTasks[index - 1].setIsDone(true);
                    return String.format(MESSAGE_TASK_MARKED, index, savedTasks[index-1]);
                }
            } else {
                return MESSAGE_NO_SUCH_INDEX;
            }
        } catch (Exception e) {
            return invalidCommandError(COMMAND_MARK, USAGE_MARK);
        }
    }
    /**
     * Marks the specified task as incomplete.
     */
    private static String unmarkCommand(String args) {
        try {
            int index = Integer.parseInt(args);
            if (index > 0 && index <= numSavedTasks) {
                if (savedTasks[index - 1].getIsDone()) {
                    savedTasks[index - 1].setIsDone(false);
                    return String.format(MESSAGE_TASK_UNMARKED, index, savedTasks[index-1]);

                } else {
                    return MESSAGE_TASK_ALREADY_UNMARKED;
                }
            } else {
                return MESSAGE_NO_SUCH_INDEX;
            }
        } catch (Exception e) {
            return invalidCommandError(COMMAND_UNMARK, USAGE_UNMARK);
        }
    }
}
