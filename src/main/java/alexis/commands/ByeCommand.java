package alexis.commands;

public class ByeCommand extends Command{

    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public ByeCommand() {
        System.out.println(GOODBYE_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
