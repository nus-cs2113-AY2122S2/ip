package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents the event command which is to be executed.
 */
public class EventCommand extends Command {

    private static final String TYPE_OF_TASK = "event";
    private static final String PREPOSITION_AT = "/at";
    private static final String PREPOSITION_ON = "/on";

    private String fullCommand;

    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * This is the execute method that runs when there is an event command.
     * The method will add the task to the list of tasks in the TaskList object.
     * It will then print the confirmation for adding the task and updates the file.
     *
     * @param tasks The TaskList object that contains the list of tasks.
     * @param ui The user interface object that allows for printing of the confirmation message.
     * @param storage The storage object which allows for the saving of the tasks to the file.
     * @throws IOException If there is an error saving tasks to the file.
     * @see IOException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdditionalException, IOException,
            DateTimeParseException {
        String description = getDescription();
        String location = getLocation();
        String date = getDate();
        LocalDate dateOfEvent = LocalDate.parse(date);
        Event event = new Event(description, location, dateOfEvent, TYPE_OF_TASK);
        tasks.addTask(event);
        ui.showAddDone(event, tasks.getSize());
        storage.save(event);
    }

    /**
     * This is the isBye method that returns whether the command is "bye".
     *
     * @return False because the command is "event".
     */
    @Override
    public boolean isBye() {
        return false;
    }

    /**
     * This is the executeFromFile method that takes in a list of tasks and adds a new event task to the list of tasks.
     *
     * @param listOfTasks This is the list of tasks that the new task is to be added to.
     * @throws AdditionalException If there is no description provided in the fullCommand.
     * @see AdditionalException
     */
    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException {
        String description = getDescription();
        String at = getLocation();
        String date = getDate();
        LocalDate dateOfEvent = LocalDate.parse(date);
        listOfTasks.add(new Event(description, at, dateOfEvent, TYPE_OF_TASK));
    }

    /**
     * This is the getDescription method that returns the description of the task from the fullCommand.
     *
     * @return The description of the task to be added.
     * @throws AdditionalException If there is no description provided in the fullCommand.
     * @see AdditionalException
     */
    private String getDescription() throws AdditionalException {
        int lengthOfTypeOfTask = TYPE_OF_TASK.length();
        int indexOfPreposition = fullCommand.indexOf(PREPOSITION_AT);
        if (indexOfPreposition == -1) {
            throw new AdditionalException("OOPS!!! You seem to have forgotten your preposition \"at\".");
        }
        String description = fullCommand.substring(lengthOfTypeOfTask, indexOfPreposition);
        String trimmedDescription = description.trim();
        if (trimmedDescription.length() < 1) {
            throw new AdditionalException("OOPS!!! The description cannot be empty.");
        }
        return trimmedDescription;
    }

    /**
     * This is the getLocation method that returns the location of the task from the fullCommand.
     *
     * @return The location of the task to be added.
     * @throws AdditionalException If there is no location provided in the fullCommand.
     * @see AdditionalException
     */
    private String getLocation() throws AdditionalException {
        int indexOfPrepositionAt = fullCommand.indexOf(PREPOSITION_AT);
        int lengthOfPrepositionAt = PREPOSITION_AT.length();
        int startingIndexOfLocation = indexOfPrepositionAt + lengthOfPrepositionAt;
        int indexOfPrepositionOn = fullCommand.indexOf(PREPOSITION_ON);
        if (indexOfPrepositionOn == -1) {
            throw new AdditionalException("OOPS!!! You seem to have forgotten your preposition \"on\".");
        }
        String location = fullCommand.substring(startingIndexOfLocation, indexOfPrepositionOn);
        String trimmedLocation = location.trim();
        if (trimmedLocation.length() < 1) {
            throw new AdditionalException("OOPS!!! The location of the event cannot be empty.");
        }
        return trimmedLocation;
    }

    /**
     * This is the getDate method that returns the date of the task from the fullCommand.
     *
     * @return The date of the task to be added.
     * @throws AdditionalException If there is no date provided in the fullCommand.
     * @see AdditionalException
     */
    private String getDate() throws AdditionalException {
        int indexOfPreposition = fullCommand.indexOf(PREPOSITION_ON);
        int lengthOfPreposition = PREPOSITION_ON.length();
        int startingIndexOfDate = indexOfPreposition + lengthOfPreposition;
        int lengthOfRequest = fullCommand.length();
        String date = fullCommand.substring(startingIndexOfDate, lengthOfRequest);
        String trimmedDate = date.trim();
        if (trimmedDate.length() < 1) {
            throw new AdditionalException("OOPS!!! The date of the event cannot be empty.");
        }
        return trimmedDate;
    }

}
