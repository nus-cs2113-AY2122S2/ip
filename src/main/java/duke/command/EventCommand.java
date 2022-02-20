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

public class EventCommand extends Command {

    private static final String TYPE_OF_TASK = "event";
    private static final String PREPOSITION_AT = "/at";
    private static final String PREPOSITION_ON = "/on";

    private String fullCommand;

    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

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

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException {
        String description = getDescription();
        String at = getLocation();
        String date = getDate();
        LocalDate dateOfEvent = LocalDate.parse(date);
        listOfTasks.add(new Event(description, at, dateOfEvent, TYPE_OF_TASK));
    }

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
