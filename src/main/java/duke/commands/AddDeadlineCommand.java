package duke.commands;

import duke.customexceptions.EmptyDescriptionException;
import duke.customexceptions.EmptyTimingDetailsException;
import duke.task.Deadline;
import duke.task.Task;

import java.util.ArrayList;

public class AddDeadlineCommand extends Command {
    public void execute(ArrayList<Task> tasks, String userInput, int taskUniqueID) {
        try {
            String description = ui.getDescription(userInput);
            String by = ui.getTimingDetails(userInput);
            Deadline newDeadline = new Deadline(description, taskUniqueID, by);
            tasks.add(newDeadline);
            ui.printMessageForAdding(tasks, newDeadline);
            taskUniqueID++;
        } catch (EmptyDescriptionException e) {
            ui.printEmptyDescriptionMessage();
        } catch (EmptyTimingDetailsException e) {
            ui.printEmptyTimingDetailsMessage();
        }
    }
}
