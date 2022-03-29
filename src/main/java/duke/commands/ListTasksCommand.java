package duke.commands;

import duke.task.Task;
import duke.userinterface.UserInterface;

import java.util.ArrayList;

/**
 * Prints out all tasks currently in the array list of tasks.
 */
public class ListTasksCommand extends Command {
    public void execute(ArrayList<Task> tasks, String userInput, int taskUniqueID) {
        UserInterface ui = new UserInterface();
        ui.printTasks(tasks);
    }
}
