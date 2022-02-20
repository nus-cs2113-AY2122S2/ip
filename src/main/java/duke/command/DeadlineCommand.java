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
        String description = getDescription(TYPE_OF_TASK, PREPOSITION, fullCommand);
        if (description.length() < 1) {
            throw new AdditionalException("What is your deadline for..?");
        }
        String date = getDate(PREPOSITION, fullCommand);
        if (date.length() < 1) {
            throw new AdditionalException("You have a deadline but you don't have a deadline?");
        }
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
        String description = getDescription(TYPE_OF_TASK, PREPOSITION, fullCommand);
        String date = getDate(PREPOSITION, fullCommand);
        if (description.length() < 1 | date.length() < 1) {
            throw new AdditionalException("Did you accidentally edit the file?");
        }
        LocalDate dateOfDeadline = LocalDate.parse(date);
        listOfTasks.add(new Deadline(description, dateOfDeadline, TYPE_OF_TASK));
    }

}
