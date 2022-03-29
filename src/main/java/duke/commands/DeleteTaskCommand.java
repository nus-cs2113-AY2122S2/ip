package duke.commands;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Removes a specific task from the array list of tasks.
 */
public class DeleteTaskCommand extends Command {
    public void execute(ArrayList<Task> tasks, String userInput, int taskUniqueID) throws NullPointerException,
            IndexOutOfBoundsException, NumberFormatException{
        int number = ui.getTaskNumber(userInput);
        ui.printTask(tasks, number);
        tasks.remove(number);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
