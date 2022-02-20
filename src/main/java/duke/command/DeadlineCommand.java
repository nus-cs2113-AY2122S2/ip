package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class DeadlineCommand extends Command {

    private static final String TYPE_OF_TASK = "deadline";
    private static final String PREPOSITION = "/by";

    private String fullCommand;

    public DeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdditionalException, IOException {
        String description = getDescription();
        String by = getBy();
        Deadline deadline = new Deadline(description, by, TYPE_OF_TASK);
        tasks.addTask(deadline);
        ui.showAddDone(deadline, tasks.getSize());
        storage.save(deadline);
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException {
        String description = getDescription();
        String by = getBy();
        listOfTasks.add(new Deadline(description, by, TYPE_OF_TASK));
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

    private String getBy() throws AdditionalException {
        int indexOfPreposition = fullCommand.indexOf(PREPOSITION);
        int lengthOfPreposition = PREPOSITION.length();
        int startingIndexOfTiming = indexOfPreposition + lengthOfPreposition;
        int lengthOfRequest = fullCommand.length();
        String timing = fullCommand.substring(startingIndexOfTiming, lengthOfRequest);
        String trimmedTiming = timing.trim();
        if (trimmedTiming.length() < 1) {
            throw new AdditionalException("OOPS!!! The timing of the deadline cannot be empty.");
        }
        return trimmedTiming;
    }

}
