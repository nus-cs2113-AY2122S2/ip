package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidArgumentException;
import duke.tasks.Task;

import java.util.HashMap;
import duke.tasks.TaskList;
import duke.Storage;
import duke.Ui;

public class FindCommand extends Command {
    private static final String FIND_PRE_MESSAGE_FORMAT = "Here are the tasks that were found:";
    private static final String FIND_MESSAGE_FORMAT =  "%d. %s";
    private static final String COMMAND_NAME = "find";
    private static final String INVALID_INPUT = "Find must have description!";

    private HashMap<String, String> arguments;

    /**
     * Initialises the arguments input by the user for this class.
     * @param parsedArguments arguments from Parser
     */
    public FindCommand(HashMap<String, String> parsedArguments) {
        this.arguments = parsedArguments;
    }

    /**
     * Asserts user arguments have either a description to search
     *
     * @throws InvalidArgumentException when argument entered is not an integer
     */
    @Override
    protected void assertArguments() throws InvalidArgumentException {
        String searchDescription = arguments.get("");
        boolean isDescriptionEmpty = (searchDescription == null || searchDescription.equals(""));
        if (isDescriptionEmpty) {
            throw new InvalidArgumentException(COMMAND_NAME, INVALID_INPUT);
        }
    }

    /**
     * Searches for tasks in the list based on an O(n) iterative search on the ArrayList
     * @param taskList the taskList to act on
     * @param ui the provided Ui to output on
     * @param storage the provided filename to update data to
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        assertArguments();
        ui.showOutput(FIND_PRE_MESSAGE_FORMAT);
        for (int i = 0; i<taskList.size(); i++) {
            Task taskToSearch = taskList.get(i);
            if (matches(taskToSearch)) {
                String taskInfo = taskToSearch.toString();
                String formattedTaskInfo = String.format(FIND_MESSAGE_FORMAT, i+1, taskInfo);
                ui.showOutput(formattedTaskInfo);
            }
        }
    }

    private boolean matches(Task taskToSearch) {
        String searchDescription = arguments.get("");
        String description = taskToSearch.getDescription();
        boolean isMatchDescription = description.contains(searchDescription);
        return isMatchDescription;
    }
}
