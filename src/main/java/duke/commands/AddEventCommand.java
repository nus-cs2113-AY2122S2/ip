package duke.commands;

import duke.customexceptions.EmptyDescriptionException;
import duke.customexceptions.EmptyTimingDetailsException;
import duke.task.Event;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Adds an Event object into the array list of tasks.
 */
public class AddEventCommand extends AddCommand {
    private boolean saveIsRequired = true;
    public void execute(ArrayList<Task> tasks, String userInput, int taskUniqueID) {
        try {
            String description = ui.getDescription(userInput);
            String at = ui.getTimingDetails(userInput);
            Event newEvent = new Event(description, taskUniqueID, at);
            tasks.add(newEvent);
            ui.printMessageForAdding(tasks, newEvent);
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
