package Commands;

import Components.Task;

import Interfaces.UI;

import Managers.TaskManager;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Command for Bao to display tasks from the task list that have the given keyword in its description.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates find command for specified keyword.
     *
     * @param keyword Word to be searched for in task description.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches task descriptions for the given keyword. Tasks with the keyword in the description are displayed.
     *
     * @param taskManager TaskManager to execute command on.
     * @param ui The interface that provides interaction with the user.
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) {
        ArrayList<Task> tasks = taskManager.getTasks();
        ArrayList<String> taskStrings = new ArrayList<>();
        String taskString;
        int numMatched = 0;

        for (Task task : tasks) {
            taskString = task.toString();
            if (taskString.toLowerCase().contains(keyword.toLowerCase())) {
                taskStrings.add(++numMatched + ". " + taskString);
            }
        }

        ui.findTasksMessage(taskStrings);
    }
}
