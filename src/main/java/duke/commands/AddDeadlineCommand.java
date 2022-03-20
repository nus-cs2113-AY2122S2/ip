package duke.commands;

import duke.customexceptions.EmptyDescriptionException;
import duke.customexceptions.EmptyTimingDetailsException;
import duke.task.Deadline;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Adds a deadline object into the array list of tasks.
 * Calls ui methods to check for input formatting and prompts user for format errors if needed.
 */
public class AddDeadlineCommand extends AddCommand {
    private boolean saveIsRequired = true;
    public void execute(ArrayList<Task> tasks, String userInput, int taskUniqueID) {
        try {
            String description = ui.getDescription(userInput);
            String by = ui.getTimingDetails(userInput);
            Deadline newDeadline = new Deadline(description, taskUniqueID, by);
            tasks.add(newDeadline);
            ui.printMessageForAdding(tasks, newDeadline);
            taskUniqueID++;
        } catch (EmptyDescriptionException e) {
            saveIsRequired = false;
            ui.printEmptyDescriptionMessage();
        } catch (EmptyTimingDetailsException e) {
            saveIsRequired = false;
            ui.printEmptyTimingDetailsMessage();
        }
    }

    public boolean isSaveRequired() {
        return saveIsRequired;
    }
}
