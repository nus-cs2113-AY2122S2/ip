package duke.commands;

import duke.exceptions.InvalidArgumentException;
import duke.tasks.Task;

import java.util.HashMap;

public class DeleteCommand extends Command {
    private static final String TASK_DELETED_MESSAGE_FORMAT =
            "____________________________________________________________"
                    + "\nNoted. I've removed this task:"
                    + "\n%s"
                    + "\nNow you have %d tasks in the list."
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
    public DeleteCommand(HashMap<String, String> parsedArguments) {
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
     * Deletes a task using ArrayList's built in remove.
     */
    public void execute() {
        try {
            assertArguments();
            Task taskToRemove = taskList.get(index);
            System.out.printf((TASK_DELETED_MESSAGE_FORMAT + "%n"), taskToRemove, taskList.size()-1);
            taskList.remove(index);
        } catch (InvalidArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            InvalidArgumentException error = new InvalidArgumentException(COMMAND_NAME, INVALID_TASK);
            System.out.println(error.getMessage());
        }
    }
}
