package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidArgumentException;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.Ui;
import duke.Storage;

import java.util.HashMap;

public class EventCommand extends Command {
    public static final String TASK_ADDED_MESSAGE_FORMAT = "added: %s";
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

    /**
     * Creates an Event task and adds it to taskList
     * @param taskList the taskList to act on
     * @param ui the provided Ui to output on
     * @param storage the provided filename to update data to
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assertArguments();
        String description = arguments.get("");
        String byDate = arguments.get("/at");
        Event eventTask = new Event(description, byDate);
        taskList.add(eventTask);
        String output = String.format(TASK_ADDED_MESSAGE_FORMAT, eventTask.toString());
        ui.showOutput(output);
        storage.write(taskList);
    }
}
