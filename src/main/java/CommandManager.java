import java.util.Scanner;


public class CommandManager {
    /**
     * Scanner to get user input
     */
    private static Scanner sc = new Scanner(System.in);

    private final static String CMD_ADD = "add";
    private final static String CMD_MARK = "mark";
    private final static String CMD_UNMARK = "unmark";
    private final static String CMD_LIST = "list";
    private final static String CMD_DEADLINE = "deadline";
    private final static String CMD_EVENT = "event";
    private final static String CMD_TODO = "todo";
    private final static String CMD_ECHO = "echo";

    /**
     * Get the input string (raw command) from user
     *
     * @return the command from user
     */
    public static Command getCommand() {
        String rawCommand = sc.nextLine();
        return new Command(rawCommand);
    }


    /**
     * Run the corresponding command/method regarding args, default command is add reminder
     *
     * @param command the command to execute
     */
    public static void runCommand(Command command) {
        String[] args = command.getCommandTokens();
        // TODO Maybe use something like a function pointer array in C
        switch (args[0]) {
        case CMD_ADD:
            addAndHandleException(args);
            break;
        case CMD_MARK:
            markAndHandleException(args);
            break;
        case CMD_UNMARK:
            unmarkAndHandleException(args);
            break;
        case CMD_LIST:
            listAndHandleException(args);
            break;
        case CMD_DEADLINE:
            addDeadlineAndHandleException(args);
            break;
        case CMD_EVENT:
            addEventAndHandleException(args);
            break;
        case CMD_TODO:
            addTodoAndHandleException(args);
        case CMD_ECHO:
            echo(args);
            break;
        default:
            defaultCmd(command);
            break;
        }

    }

    private static void defaultCmd(Command command) {
        System.out.println("Sorry I don't understand this sentence:\n\t\t" + command.getRawCommand() + "\n");
    }

    private static void addTodoAndHandleException(String[] args) {
        try {
            TaskManager.addToDoes(args);
            TaskManager.printTaskNumber();
        } catch (DukeException exception) {
            System.out.println(exception);
        }
    }

    private static void addEventAndHandleException(String[] args) {
        try {
            TaskManager.addEvents(args);
            TaskManager.printTaskNumber();
        } catch (DukeException exception) {
            System.out.println(exception);
        }
    }

    private static void addDeadlineAndHandleException(String[] args) {
        try {
            TaskManager.addDeadlines(args);
            TaskManager.printTaskNumber();
        } catch (DukeException exception) {
            System.out.println(exception);
        }
    }

    private static void listAndHandleException(String[] args) {
        try {
            TaskManager.listTasks(args);
        } catch (DukeException exception) {
            System.out.println(exception);
        }
    }

    private static void unmarkAndHandleException(String[] args) {
        try {
            TaskManager.unmark(args);
        } catch (DukeException exception) {
            System.out.println(exception);
        }
    }

    private static void markAndHandleException(String[] args) {
        try {
            TaskManager.mark(args);
        } catch (DukeException exception) {
            System.out.println(exception);
        }
    }

    private static void addAndHandleException(String[] args) {
        try {
            TaskManager.addTasks(args);
            TaskManager.printTaskNumber();
        } catch (DukeException exception) {
            System.out.println(exception);
        }
    }

    /**
     * Print every token in args
     *
     * @param args tokens to print
     */
    private static void echo(String[] args) {
        if (!args[0].equals("echo")) {
            // there must be some error
            // TODO exception handle
            return;
        }
        for (int i = 1; i < args.length; i++) {
            System.out.print(args[i]);
            if (i != args.length - 1) {
                System.out.print(" ");
            } else {
                System.out.print("\n");
            }
        }
    }
}
