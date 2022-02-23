package duke.commands;

import duke.customexceptions.EmptyDescriptionException;
import duke.customexceptions.EmptyTimingDetailsException;
import duke.task.Event;
import duke.task.Task;

import java.util.ArrayList;

public class AddEventCommand extends Command {
    public void execute(ArrayList<Task> tasks, String userInput, int taskUniqueID) {
        try {
            String description = ui.getDescription(userInput);
            String at = ui.getTimingDetails(userInput);
            Event newEvent = new Event(description, taskUniqueID, at);
            tasks.add(newEvent);
            ui.printMessageForAdding(tasks, newEvent);
            taskUniqueID++;
        } catch (EmptyDescriptionException e) {
            ui.printEmptyDescriptionMessage();
        } catch (EmptyTimingDetailsException e) {
            ui.printEmptyDescriptionMessage();
        }
    }
}
