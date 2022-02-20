package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class EventCommand extends Command {

    private static final String TYPE_OF_TASK = "event";
    private static final String PREPOSITION = "/at";

    private String fullCommand;

    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdditionalException, IOException {
        String description = getDescription();
        String at = getAt();
        Event event = new Event(description, at, TYPE_OF_TASK);
        tasks.addTask(event);
        ui.showAddDone(event, tasks.getSize());
        storage.save(event);
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException {
        String description = getDescription();
        String at = getAt();
        listOfTasks.add(new Event(description, at, TYPE_OF_TASK));
    }

    private String getDescription() throws AdditionalException {
        int lengthOfTypeOfTask = TYPE_OF_TASK.length();
        int indexOfPreposition = fullCommand.indexOf(PREPOSITION);
        if (indexOfPreposition == -1) {
            throw new AdditionalException("OOPS!!! You seem to have forgotten your preposition.");
        }
        String description = fullCommand.substring(lengthOfTypeOfTask, indexOfPreposition);
        String trimmedDescription = description.trim();
        if (trimmedDescription.length() < 1) {
            throw new AdditionalException("OOPS!!! The description cannot be empty.");
        }
        return trimmedDescription;
    }

    private String getAt() throws AdditionalException {
        int indexOfPreposition = fullCommand.indexOf(PREPOSITION);
        int lengthOfPreposition = PREPOSITION.length();
        int startingIndexOfTiming = indexOfPreposition + lengthOfPreposition;
        int lengthOfRequest = fullCommand.length();
        String at = fullCommand.substring(startingIndexOfTiming, lengthOfRequest);
        String trimmedAt = at.trim();
        if (trimmedAt.length() < 1) {
            throw new AdditionalException("OOPS!!! The timing of the event cannot be empty.");
        }
        return trimmedAt;
    }

}
