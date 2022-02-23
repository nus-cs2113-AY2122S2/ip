package duke.commands;

import duke.customexceptions.EmptyDescriptionException;
import duke.task.Task;

import java.util.ArrayList;

public class FindTasksCommand extends Command {
    public void execute(ArrayList<Task> tasks, String userInput, int taskUniqueID) {
        try {
            String keywords = ui.getDescription(userInput);
            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (Task task : tasks) {
                if (task.getDescription().contains(keywords)) {
                    matchingTasks.add(task);
                }
            }
            ui.printMatchingTasks(matchingTasks);
        } catch (EmptyDescriptionException e) {
            ui.printEmptyDescriptionMessage();
        }
    }
}
