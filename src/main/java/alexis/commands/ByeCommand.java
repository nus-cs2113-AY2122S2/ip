package alexis.commands;

/**
 * Displays bye message to user and ends the program
 */
public class ByeCommand extends Command{

    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public ByeCommand() {
        System.out.println(GOODBYE_MESSAGE);
    }

    /**
     * Sets isExit() to true, which will cause the run() method in the Alexis class to end the program
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
