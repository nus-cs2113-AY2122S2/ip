package duke.commands;

import duke.exceptions.InvalidArgumentException;
import duke.tasks.Deadline;

import java.util.HashMap;

public class DeadlineCommand extends Command {
    public static final String TASK_ADDED_MESSAGE_FORMAT =
            "____________________________________________________________"
            + "\nadded: %s"
            + "\n____________________________________________________________";
    private static final String COMMAND_NAME = "deadline";
    private static final String EMPTY_ARGUMENTS = "Deadline must have a description!";
    private static final String EMPTY_BYDATE = "Deadline must have a valid date for /by!";

    private HashMap<String, String> arguments;
    /**
     * Initialises the arguments input by the user for this class.
     * @param parsedArguments arguments from Parser
     */
    public DeadlineCommand(HashMap<String, String> parsedArguments) {
        this.arguments = parsedArguments;
    }

    /**
     * Asserts user arguments are legal for Deadline by checking whether description and byDate exists. (Key "", "/by")
     *
     * @throws InvalidArgumentException when argument entered is not an integer
     */
    @Override
    protected void assertArguments() throws InvalidArgumentException {
        String errorMsg = "";
        if (arguments.get("")==null || arguments.get("").equals("")) {
            errorMsg += EMPTY_ARGUMENTS+"\n";
        }
        if (arguments.get("/by")==null || arguments.get("").equals("")) {
            errorMsg += EMPTY_BYDATE+"\n";
        }
        if (!errorMsg.equals("")) {
            throw new InvalidArgumentException(COMMAND_NAME,errorMsg.trim());
        }
    }

    /**
     * Creates an Event task and adds it to taskList
     */
    public void execute() {
        try {
            assertArguments();
            String description = arguments.get("");
            String byDate = arguments.get("/by");
            Deadline deadlineTask = new Deadline(description, byDate);
            taskList.add(deadlineTask);
            System.out.printf(TASK_ADDED_MESSAGE_FORMAT, deadlineTask);
        } catch (InvalidArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}