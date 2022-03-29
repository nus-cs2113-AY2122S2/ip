package duke.commands;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Mark or unmark a specific task currently in the array list of tasks.
 */
public class MarkOrUnmarkTaskCommand extends Command {
    public void execute(ArrayList<Task> tasks, String userInput, int taskUniqueID) throws NullPointerException,
            IndexOutOfBoundsException, NumberFormatException {
        String command = ui.getCommand(userInput);
        int number = ui.getTaskNumber(userInput);
        ui.printMarkOrUnmarkMessage(tasks, command, number);
    }
}
