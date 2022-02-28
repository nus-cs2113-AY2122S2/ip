package commands;

import tasks.TaskList;
import static common.Message.LIST_MESSAGE;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public ExecutedCommandResults executeCommand(TaskList tasks) {
        return new ExecutedCommandResults(LIST_MESSAGE, tasks);
    }
}
