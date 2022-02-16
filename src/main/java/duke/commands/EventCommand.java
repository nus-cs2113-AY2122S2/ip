package duke.commands;

import duke.exceptions.InvalidArgumentException;
import duke.tasks.Event;

import java.util.HashMap;

public class EventCommand extends Command {
    public static final String TASK_ADDED_MESSAGE_FORMAT =
            "____________________________________________________________"
                    + "\nadded: %s"
                    + "\n____________________________________________________________";
    private static final String COMMAND_NAME = "deadline";
    private static final String EMPTY_ARGUMENTS = "Event must have a description!";
    private static final String EMPTY_BYDATE = "Event must have a valid date for /at!";

    private HashMap<String, String> arguments;
    /**
     * Initialises the arguments input by the user for this class.
     * @param parsedArguments arguments from Parser
     */
    public EventCommand(HashMap<String, String> parsedArguments) {
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
        if (arguments.get("/at")==null || arguments.get("").equals("")) {
            errorMsg += EMPTY_BYDATE+"\n";
        }
        if (!errorMsg.equals("")) {
            throw new InvalidArgumentException(COMMAND_NAME,errorMsg.trim());
        }
    }

    public void execute() {
        try {
            assertArguments();
            String description = arguments.get("");
            String byDate = arguments.get("/by");
            Event eventTask = new Event(description, byDate);
            taskList.add(eventTask);
            System.out.println(String.format(TASK_ADDED_MESSAGE_FORMAT, eventTask.toString()));
        } catch (InvalidArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
