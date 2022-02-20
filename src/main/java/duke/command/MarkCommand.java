package duke.command;

import java.util.ArrayList;

import static duke.common.Strings.*;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        super();
        this.index = index;
    }

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
        if (taskList.getTask(index - 1).getIsDone()) {
            commandFeedback.add(MESSAGE_TASK_ALREADY_MARKED);
            return commandFeedback;
        }
        taskList.getTask(index - 1).setIsDone(true);
        commandFeedback.add(MESSAGE_TASK_MARKED);
        commandFeedback.add(String.format(MESSAGE_TASK_DISPLAY_FORMAT, index, taskList.getTask(index - 1)));
        //saveToFile();
        return commandFeedback;
    }
}
