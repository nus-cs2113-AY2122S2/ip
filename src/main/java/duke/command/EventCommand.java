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
        String description = getDescription(TYPE_OF_TASK, PREPOSITION_AT, fullCommand);
        if (description.length() < 1) {
            throw new AdditionalException("Yea... I know you don't have an event because you're a loner.");
        }
        String location = getLocation(PREPOSITION_AT, PREPOSITION_ON, fullCommand);
        if (location.length() < 1) {
            throw new AdditionalException("So your event is at nowhere-land?");
        }
        String date = getDate(PREPOSITION_ON, fullCommand);
        if (date.length() < 1) {
            throw new AdditionalException("YAY! Your event is never going to happen :DDDD");
        }
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
        String description = getDescription(TYPE_OF_TASK, PREPOSITION_AT, fullCommand);
        String location = getLocation(PREPOSITION_AT, PREPOSITION_ON, fullCommand);
        String date = getDate(PREPOSITION_ON, fullCommand);
        if (description.length() < 1 | location.length() < 1 | date.length() < 1) {
            throw new AdditionalException("YAY! Your event is never going to happen :DDDD");
        }
        LocalDate dateOfEvent = LocalDate.parse(date);
        listOfTasks.add(new Event(description, location, dateOfEvent, TYPE_OF_TASK));
    }

}
