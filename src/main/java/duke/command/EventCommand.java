package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.parser.Parser;
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
    private static final int MINIMUM_LENGTH = 1;

    private String fullCommand;

    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Runs when there is an event command by adding the event task to the list of tasks.
     * It will then print the confirmation for adding the event task and updates the file.
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
        String description = Parser.getDescription(TYPE_OF_TASK, PREPOSITION_AT, fullCommand);
        if (description.length() < MINIMUM_LENGTH) {
            throw new AdditionalException("Yea... I know you don't have an event because you're a loner.");
        }
        String location = Parser.getLocation(PREPOSITION_AT, PREPOSITION_ON, fullCommand);
        if (location.length() < MINIMUM_LENGTH) {
            throw new AdditionalException("So your event is at nowhere-land?");
        }
        String date = Parser.getDate(PREPOSITION_ON, fullCommand);
        if (date.length() < MINIMUM_LENGTH) {
            throw new AdditionalException("YAY! Your event is never going to happen :DDDD");
        }
        LocalDate dateOfEvent = LocalDate.parse(date);
        Event event = new Event(description, location, dateOfEvent, TYPE_OF_TASK);
        tasks.addTask(event);
        ui.showAddDone(event, tasks.getSize());
        storage.save(event);
    }

    /**
     * Adds a new event task from the file to the list of tasks.
     *
     * @param listOfTasks This is the list of tasks that the new task is to be added to.
     * @throws AdditionalException If there is no description provided in the fullCommand.
     * @see AdditionalException
     */
    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException {
        String description = Parser.getDescription(TYPE_OF_TASK, PREPOSITION_AT, fullCommand);
        String location = Parser.getLocation(PREPOSITION_AT, PREPOSITION_ON, fullCommand);
        String date = Parser.getDate(PREPOSITION_ON, fullCommand);
        boolean isDescriptionMissing = description.length() < MINIMUM_LENGTH;
        boolean isLocationMissing = location.length() < MINIMUM_LENGTH;
        boolean isDateMissing = date.length() < MINIMUM_LENGTH;
        boolean isDescriptionOrLocationMissing = isDescriptionMissing || isLocationMissing;
        boolean isInformationMissing = isDescriptionOrLocationMissing || isDateMissing;
        if (isInformationMissing) {
            throw new AdditionalException("YAY! Your event is never going to happen :DDDD");
        }
        LocalDate dateOfEvent = LocalDate.parse(date);
        listOfTasks.add(new Event(description, location, dateOfEvent, TYPE_OF_TASK));
    }

}
