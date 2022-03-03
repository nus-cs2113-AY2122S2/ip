package Commands;

import Components.Task;
import Interfaces.UI;
import Managers.TaskManager;

import java.util.ArrayList;

import static Constants.BaoConstants.LINE_BREAK;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskManager taskManager, UI ui) {
        ArrayList<Task> tasks = taskManager.getTasks();
        String taskString;
        int numMatched = 0;

        System.out.println(LINE_BREAK);
        System.out.println("Here are the tasks you asked for: ");
        for (Task task : tasks) {
            taskString = task.toString();
            if(taskString.contains(keyword)) {
                System.out.println(++numMatched + ". " +taskString);
            }
        }
        System.out.println(LINE_BREAK);
    }
}
