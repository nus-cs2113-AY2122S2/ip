package alexis.commands;

/**
 * Prints incorrect command message to user
 */
public class IncorrectCommand extends Command{

    public static final String INVALID_INPUT_MESSAGE = "Oops!! I'm sorry, but I don't know what that means :-(";

    /**
     * Prints incorrect input message
     */
    public IncorrectCommand() {
        System.out.println(INVALID_INPUT_MESSAGE);
    }

}
