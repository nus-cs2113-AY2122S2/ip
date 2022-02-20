package duke.command;

import duke.data.task.Task;

import java.util.ArrayList;

import static duke.common.Strings.*;

/**
 * Deletes the task with the specified index from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public ArrayList<String> execute() {
        if (taskList == null) {
            commandFeedback.add(MESSAGE_IMPOSSIBLE);
            commandFeedback.add(null);
            return commandFeedback;
        }
        if (index < 1 || index > taskList.getNumTasks()) {
            commandFeedback.add(MESSAGE_NO_SUCH_INDEX);
            return commandFeedback;
        }
        Task removedTask = taskList.removeTask(index - 1);
        commandFeedback.add(MESSAGE_TASK_DELETED);
        commandFeedback.add(removedTask.toString());
        return commandFeedback;
    }
}
