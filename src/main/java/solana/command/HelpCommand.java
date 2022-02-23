package solana.command;

/**
 * Represents the Help command. This class also serves as a dummy class to return when an invalid command is
 * received.
 */
public class HelpCommand extends Command {
    boolean isExceptionHandled;

    public HelpCommand(boolean isExceptionHandled) {
        this.isExceptionHandled = isExceptionHandled;
    }

    /**
     * Prints out an error message when an invalid command is received and has not been handled.
     */
    @Override
    public void executeCommand() {
        if (!isExceptionHandled) {
            System.out.println("Invalid Command!" + System.lineSeparator());
        }
    }
}
