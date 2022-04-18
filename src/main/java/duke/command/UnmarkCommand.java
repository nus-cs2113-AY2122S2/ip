package duke.command;

import java.util.ArrayList;

import static duke.common.Strings.*;

/**
 * Marks a specified task as uncompleted.
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
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
        if (!taskList.getTask(index - 1).getIsDone()) {
            commandFeedback.add(MESSAGE_TASK_ALREADY_UNMARKED);
            return commandFeedback;
        }
        taskList.getTask(index - 1).setIsDone(false);
        commandFeedback.add(MESSAGE_TASK_UNMARKED);
        commandFeedback.add(String.format(MESSAGE_TASK_DISPLAY_FORMAT, index, taskList.getTask(index - 1)));
        //saveToFile();
        return commandFeedback;
    }
}
