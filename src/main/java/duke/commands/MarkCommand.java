package duke.commands;

import duke.exceptions.InvalidArgumentException;
import duke.tasks.Task;

import java.util.HashMap;

public class MarkCommand extends Command {
    private static final String TASK_MARKED_MESSAGE_FORMAT =
            "____________________________________________________________"
            + "\nNice! I've marked this task as done:"
            + "\n%s"
            + "\n____________________________________________________________";
    private static final String COMMAND_NAME = "mark";
    private static final String INVALID_INPUT = "The argument received is not a valid integer.";
    public static final String INVALID_TASK = "The task number given does not exist";

    private int index;
    private HashMap<String, String> arguments;

    /**
     * Initialises the arguments input by the user for this class.
     * @param parsedArguments arguments from Parser
     */
    public MarkCommand(HashMap<String, String> parsedArguments) {
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
     * Marks a task as done, with index specified by user.
     */
    public void execute() {
        try {
            assertArguments();
            Task taskToMark = this.taskList.get(index);
            taskToMark.setIsDone(true);
            System.out.printf((TASK_MARKED_MESSAGE_FORMAT) + "%n", taskToMark);
        } catch (InvalidArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            InvalidArgumentException error = new InvalidArgumentException(COMMAND_NAME, INVALID_TASK);
            System.out.println(error.getMessage());
        }
    }
}
