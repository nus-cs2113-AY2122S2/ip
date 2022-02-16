package duke.commands;

import duke.exceptions.InvalidArgumentException;
import duke.tasks.Todo;

import java.util.HashMap;

public class TodoCommand extends Command {
    private static final String TASK_ADDED_MESSAGE_FORMAT =
            "____________________________________________________________"
            + "\nadded: %s"
            + "\n____________________________________________________________";
    private static final String COMMAND_NAME = "todo";
    private static final String EMPTY_ARGUMENTS = "Todo must have a description!";

    private HashMap<String, String> arguments;
    /**
     * Initialises the arguments input by the user for this class.
     * @param parsedArguments arguments from Parser
     */
    public TodoCommand(HashMap<String, String> parsedArguments) {
        this.arguments = parsedArguments;
    }

    /**
     * Asserts user arguments are legal for Todo by checking whether description and byDate exists. (Key "")
     *
     * @throws InvalidArgumentException when argument entered is not an integer
     */
    @Override
    protected void assertArguments() throws InvalidArgumentException {
        if (arguments.get("").equals("")) {
            throw new InvalidArgumentException(COMMAND_NAME, EMPTY_ARGUMENTS);
        }
    }

    public void execute() {
        try {
            assertArguments();
            String description = arguments.get("");
            Todo todoTask = new Todo(description);
            taskList.add(todoTask);
            System.out.println(String.format(TASK_ADDED_MESSAGE_FORMAT, todoTask.toString()));
        } catch (InvalidArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
