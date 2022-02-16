package duke.commands;

import duke.exceptions.InvalidArgumentException;
import duke.tasks.Task;

import java.util.HashMap;

public class UnmarkCommand extends Command {
    private static final String TASK_UNMARKED_MESSAGE_FORMAT =
            "____________________________________________________________"
            + "\nOK, I've marked this task as not done yet:"
            + "\n %s"
            + "\n____________________________________________________________";
    private static final String COMMAND_NAME = "unmark";
    private static final String INVALID_INPUT = "The argument received is not a valid integer.";
    private static final String INVALID_TASK = "The task number given does not exist";

    private Integer index;
    private HashMap<String, String> arguments;

    /**
     * Initialises the arguments input by the user for this class.
     * @param parsedArguments arguments from Parser
     */
    public UnmarkCommand(HashMap<String, String> parsedArguments) {
        this.arguments = parsedArguments;
    }

    /**
     * Asserts user arguments can be converted to integer
     *
     * @throws InvalidArgumentException when argument entered is not an integer
     */
    @Override
    protected void assertArguments() throws InvalidArgumentException {
        try {
            this.index = Integer.parseInt(arguments.get(""))-1;
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(COMMAND_NAME, INVALID_INPUT);
        }
    }

    /**
     * Marks a task as not done, with index specified by user.
     */
    public void execute() {
        try {
            assertArguments();
            Task taskToMark = taskList.get(index);
            taskToMark.setIsDone(false);
            System.out.printf((TASK_UNMARKED_MESSAGE_FORMAT) + "%n", taskToMark);
        } catch (InvalidArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            InvalidArgumentException error = new InvalidArgumentException(COMMAND_NAME, INVALID_TASK);
            System.out.println(error.getMessage());
        }
    }
}
