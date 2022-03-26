package commands;

import tasks.TaskList;
import static common.Message.LIST_MESSAGE;

/**
 * This class handles execution of the list command.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * @inheritDoc
     */
    @Override
    public ExecutedCommandResults executeCommand(TaskList tasks) {
        return new ExecutedCommandResults(LIST_MESSAGE, tasks);
    }
}
