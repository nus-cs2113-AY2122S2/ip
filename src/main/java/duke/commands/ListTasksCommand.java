package duke.commands;

import duke.task.Task;
import duke.userinterface.UserInterface;

import java.util.ArrayList;

public class ListTasksCommand extends Command {
    public void execute(ArrayList<Task> tasks, String userInput, int taskUniqueID) {
        UserInterface ui = new UserInterface();
        ui.printTasks(tasks);
    }
}
