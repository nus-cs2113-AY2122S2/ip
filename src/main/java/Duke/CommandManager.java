package Duke;

import java.io.IOException;
import java.util.Scanner;


public class CommandManager {
    /**
     * Scanner to get user input.
     */
    private static Scanner sc = new Scanner(System.in);

    private final static String CMD_MARK = "mark";
    private final static String CMD_UNMARK = "unmark";
    private final static String CMD_LIST = "list";
    private final static String CMD_DEADLINE = "deadline";
    private final static String CMD_EVENT = "event";
    private final static String CMD_TODO = "todo";
    private final static String CMD_ECHO = "echo";
    private final static String CMD_DELETE = "delete";

    /**
     * Get the input string (raw command) from user.
     *
     * @return the command from user
     */
    public static Command getCommand() {
        String rawCommand = sc.nextLine();
        return new Command(rawCommand);
    }


    /**
     * Run the corresponding command/method regarding args, default command is add reminder.
     *
     * @param command the command to execute
     */
    public static void runCommand(Command command) throws DukeException {
        String[] args = command.getCommandTokens();
        // TODO Maybe use something like a function pointer array in C
        try {
            switch (args[0]) {
            case CMD_MARK:
                TaskManager.mark(args, true);
                DiskManager.syncWithDisk();
                break;
            case CMD_UNMARK:
                TaskManager.unmark(args);
                DiskManager.syncWithDisk();
                break;
            case CMD_LIST:
                TaskManager.listTasks(args);
                break;
            case CMD_DELETE:
                TaskManager.delete(args);
                DiskManager.syncWithDisk();
                break;
            case CMD_DEADLINE:
                TaskManager.addDeadlines(args, true);
                TaskManager.printTaskNumber();
                DiskManager.syncWithDisk();
                break;
            case CMD_EVENT:
                TaskManager.addEvents(args, true);
                TaskManager.printTaskNumber();
                DiskManager.syncWithDisk();
                break;
            case CMD_TODO:
                TaskManager.addToDoes(args, true);
                TaskManager.printTaskNumber();
                DiskManager.syncWithDisk();
                break;
            case CMD_ECHO:
                echo(args);
                break;
            default:
                defaultCmd(command);
                break;
            }
        } catch (DukeException | IOException exception) {
            throw new DukeException("when running command: \n" + exception.getMessage());
        }
    }

    private static void defaultCmd(Command command) {
        System.out.println("Sorry I don't understand this sentence:\n\t\t"
                + command.getRawCommand() + "\n");
    }

    /**
     * Print every token in args.
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
