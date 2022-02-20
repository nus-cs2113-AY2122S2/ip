package duke.command;

import java.util.ArrayList;

import static duke.common.Strings.*;

public class FindCommand extends Command {
    protected final String searchString;

    public FindCommand(String searchString) {
        super();
        this.searchString = searchString;
    }

    public ArrayList<String> execute() {
        if (taskList == null) {
            commandFeedback.add(MESSAGE_IMPOSSIBLE);
            commandFeedback.add(null);
            return commandFeedback;
        }
        commandFeedback.add(String.format(MESSAGE_SHOW_STRING_FILTER, searchString));
        int numTasks = taskList.getNumMatchingTasks(searchString);
        if (numTasks == 0) {
            commandFeedback.add(MESSAGE_NO_TASKS);
        } else {
            for (int i = 0; i < taskList.getNumTasks(); i++) {
                if (taskList.getTask(i).getDescription().contains(searchString)) {
                    commandFeedback.add(String.format(MESSAGE_ITEMIZED_TASK, i + 1, taskList.getTask(i)));
                }
            }
        }
        return commandFeedback;
    }
}
