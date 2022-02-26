package solana.command;

/**
 * Represents the Help command. This class also serves as a dummy class to return when an invalid command is
 * received.
 */
public class HelpCommand extends Command {
    boolean isFromUser;
    boolean isExceptionHandled;

    public HelpCommand(boolean isFromUser, boolean isExceptionHandled) {
        this.isFromUser = isFromUser;
        this.isExceptionHandled = isExceptionHandled;
    }

    /**
     * Prints out the help page if the user requested for it. If not, it means an invalid command was received,
     * and prints out an error message if the exception has not been handled.
     */
    @Override
    public void executeCommand() {
        if (isFromUser) {
            String helpPage = "-------------------------Help Page-------------------------\n"
                    + "1. Adding a Todo task: todo [TASK]\n"
                    + "2. Adding a Deadline task: deadline [TASK] /by [DUE_DATE]\n"
                    + "3. Adding an Event task: event [TASK] /at [START_TIME]\n"
                    + "4. Listing all tasks: list\n"
                    + "5. Deleting a task: delete [INDEX]\n"
                    + "6. Marking a task as done: mark [INDEX]\n"
                    + "7. Marking a task as undone: unmark [INDEX]\n"
                    + "8. Finding a task: find [KEYWORD]\n"
                    + "9. Exiting the program: bye\n"
                    + "-----------------------------------------------------------\n";

            System.out.println(helpPage);
            return;
        }

        if (!isExceptionHandled){
            System.out.println("Invalid command! Type \"help\" to see the list of supported commands" + System.lineSeparator());
        }
    }
}
