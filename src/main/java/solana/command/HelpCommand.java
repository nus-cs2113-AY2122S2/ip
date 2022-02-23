package solana.command;

public class HelpCommand extends Command {
    boolean isExceptionHandled;

    public HelpCommand(boolean isExceptionHandled) {
        this.isExceptionHandled = isExceptionHandled;
    }

    @Override
    public void executeCommand() {
        if (!isExceptionHandled) {
            System.out.println("Invalid Command!" + System.lineSeparator());
        }
    }
}
