import java.util.ArrayList;
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
                botResponse(TaskManager.run(operation, arguments));
            } catch (BobaException e) {
                ArrayList<String> responses = new ArrayList<>();
                responses.add(e.getMessage());
                botResponse(responses);
            }

        } while(operation != Command.EXIT);
        sayGoodbye();
    }

    /**
     * The initial response the bot gives starting up
     */
    private static void giveIntroduction() {
        ArrayList<String> responses = new ArrayList<>();
        responses.add("Hello! I'm Boba.");
        responses.add("I am a bot 'tasked' to manage your tasks");
        responses.add("What can I do for you?");
        responses.add("Type 'help' to get the list commands I response to");
        botResponse(responses);
    }

    /**
     * The final response after saying bye
     */
    private static void sayGoodbye() {
        ArrayList<String> responses = new ArrayList<>();
        responses.add("Bye. Hope to see you again soon!");
        botResponse(responses);
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
                throw new BobaException("Slash command is required");
            }
            arguments[1] = input.substring(input.indexOf(" ", slashIndex) + 1);
            input = input.substring(0, slashIndex - 1);
        case TODO:
        case MARK:
        case UNMARK:
            int spaceIndex = input.indexOf(" ");
            if (spaceIndex == -1) {
                throw new BobaException("Please also provide the proper arguments");
            }
            arguments[0] = input.substring(spaceIndex + 1);
        default:
            // do nothing. default has no arguments, only the command
        }
        return arguments;
    }

    /**
     * The response the bot gives based on the input by the user.
     * @param responses Collection of all the lines the bot responds with
     */
    private static void botResponse(ArrayList<String> responses) {
        System.out.println("............................................................");
        for (String response : responses) {
            System.out.println("\t" + response);
        }
        System.out.println("............................................................");
    }
}
