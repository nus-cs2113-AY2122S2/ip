import java.util.Scanner;

/**
 * A chat-bot that manages tasks for the user.
 * Number of tasks are limited to 100.
 */
public class Boba {

    /** Similar to String[] args,
     * this limit is imposed on how many arguments follow the initial command
     */
    private static final int SECTION_LIMIT = 2;

    /**
     * Main method that runs our chat-bot. Handling commands until user exits.
     */
    public static void main(String[] args) {
        giveIntroduction();

        // Scanner is how commands are inputted
        Scanner scan = new Scanner(System.in);
        Command operation = Command.NONE;

        // Keep going until the user enters 'bye'
        do {
            try {
                String input = scan.nextLine();
                operation = Command.getCommand(input);
                String[] arguments = parseInput(operation, input);
                TaskManager.run(operation, arguments);
            } catch (BobaException e) {
                ErrorHandler.printErrorMessage(e.getOperation());
            }

        } while(operation != Command.EXIT);
        sayGoodbye();
    }

    /**
     * The initial response the bot gives starting up
     */
    private static void giveIntroduction() {
        BobaResponse.addResponse("Hello! I'm Boba.");
        BobaResponse.addResponse("I am a bot 'tasked' to manage your tasks");
        BobaResponse.addResponse("What can I do for you?");
        BobaResponse.addResponse("Type 'help' to get the list commands I response to");
        BobaResponse.printResponse();
    }

    /**
     * The final response after saying bye
     */
    private static void sayGoodbye() {
        BobaResponse.printThis("Bye. Hope to see you again soon!");
    }

    /**
     * Parses the remaining input for the arguments following the command.
     * Number of arguments is determined by what command.
     * @param operation The current command being used
     * @param input Input by the user given to the bot
     * @return The arguments
     */
    private static String[] parseInput(Command operation, String input) throws BobaException{
        String[] arguments = new String[SECTION_LIMIT];
        switch (operation) {
        case DEADLINE:
        case EVENT:
            // both DEADLINE and EVENT have two arguments
            // both follow the same convention of using a slash command
            int slashIndex = input.indexOf("/");
            if (slashIndex == -1) {
                throw new BobaException(operation);
            }
            arguments[1] = input.substring(input.indexOf(" ", slashIndex) + 1);
            input = input.substring(0, slashIndex - 1);
        case TODO:
        case MARK:
        case UNMARK:
            int spaceIndex = input.indexOf(" ");
            if (spaceIndex == -1) {
                throw new BobaException(operation);
            }
            arguments[0] = input.substring(spaceIndex + 1);
        default:
            // do nothing. default has no arguments, only the command
        }
        return arguments;
    }
}
