package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents the deadline command which is to be executed.
 */
public class DeadlineCommand extends Command {

    private static final String TYPE_OF_TASK = "deadline";
    private static final String PREPOSITION = "/by";

    private String fullCommand;

    public DeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * This is the execute method that runs when there is an deadline command.
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
        String date = getDate();
        LocalDate dateOfDeadline = LocalDate.parse(date);
        Deadline deadline = new Deadline(description, dateOfDeadline, TYPE_OF_TASK);
        tasks.addTask(deadline);
        ui.showAddDone(deadline, tasks.getSize());
        storage.save(deadline);
    }

    /**
     * This is the isBye method that returns whether the command is "bye".
     *
     * @return False because the command is "deadline".
     */
    @Override
    public boolean isBye() {
        return false;
    }

    /**
     * This is the executeFromFile method that takes in a list of tasks and adds a new deadline task to the
     * list of tasks.
     *
     * @param listOfTasks This is the list of tasks that the new task is to be added to.
     * @throws AdditionalException If there is no description provided in the fullCommand.
     * @see AdditionalException
     */
    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException {
        String description = getDescription();
        String date = getDate();
        LocalDate dateOfDeadline = LocalDate.parse(date);
        listOfTasks.add(new Deadline(description, dateOfDeadline, TYPE_OF_TASK));
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
        int indexOfPreposition = fullCommand.indexOf(PREPOSITION);
        if (indexOfPreposition == -1) {
            throw new AdditionalException("OOPS!!! You seem to have forgotten your preposition \"by\".");
        }
        String description = fullCommand.substring(lengthOfTypeOfTask, indexOfPreposition);
        String trimmedDescription = description.trim();
        if (trimmedDescription.length() < 1) {
            throw new AdditionalException("OOPS!!! The description cannot be empty.");
        }
        return trimmedDescription;
    }


    /**
     * This is the getDate method that returns the date of the task from the fullCommand.
     *
     * @return The date of the task to be added.
     * @throws AdditionalException If there is no date provided in the fullCommand.
     * @see AdditionalException
     */
    private String getDate() throws AdditionalException {
        int indexOfPreposition = fullCommand.indexOf(PREPOSITION);
        int lengthOfPreposition = PREPOSITION.length();
        int startingIndexOfDate = indexOfPreposition + lengthOfPreposition;
        int lengthOfRequest = fullCommand.length();
        String date = fullCommand.substring(startingIndexOfDate, lengthOfRequest);
        String trimmedDate = date.trim();
        if (trimmedDate.length() < 1) {
            throw new AdditionalException("OOPS!!! The date of the deadline cannot be empty.");
        }
        return trimmedDate;
    }

}
