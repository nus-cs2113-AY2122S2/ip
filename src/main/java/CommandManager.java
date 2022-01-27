import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CommandManager {
    /** Scanner to get user input */
    private static Scanner sc = new Scanner(System.in);
    /** Manage the reminder function */
    private static ReminderManager reminderManager;

    /**
     *  Get the input string (raw command) from user
     * @return the command from user
     */
    public static Command getCommand() {
        String rawCommand = sc.nextLine();
        return new Command(rawCommand);
    }


    /**
     * Run the corresponding command/method regarding args, default command is add reminder
     * @param command the command to execute
     */
    public static void runCommand(Command command) {
        String[] args = command.getCommandTokens();
        // TODO Maybe use something like a function pointer array in C
        switch (args[0]) {
        case "add":
            reminderManager.add(args);
            break;
        case "mark":
            reminderManager.mark(args);
            break;
        case "unmark":
            reminderManager.unmark(args);
            break;
        case "list":
            reminderManager.list(args);
            break;
        case "echo":
            echo(args);
            break;
        default:
            String[] defaultArgs = new String[2];
            defaultArgs[0] = "add";
            defaultArgs[1] = command.getRawCommand();
            reminderManager.add(defaultArgs);
            break;
        }

    }

    /**
     * Print every token in args
     * @param args tokens to print
     */
    private static void echo(String[] args) {
        if(!args[0].equals(args[0])) {
            // there must be some error
            // TODO exception handle
            return;
        }
        for(int i = 1; i < args.length; i++) {
            System.out.print(args[i]);
            if(i != args.length - 1) {
                System.out.print(" ");
            } else {
                System.out.print("\n");
            }
        }
    }
}
