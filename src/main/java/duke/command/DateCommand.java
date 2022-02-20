package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents the date command which is to be executed.
 */
public class DateCommand extends Command {

    private String fullCommand;

    public DateCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * This is the execute method that runs when there is a date command.
     * The method will search through the list of tasks that is stored in the TaskList tasks and print the tasks
     * that match the date which the user input.
     *
     * @param tasks The TaskList object that contains the list of tasks.
     * @param ui The user interface object that allows for printing of the confirmation message.
     * @param storage The storage object which allows for the saving of the tasks to the file.
     * @throws AdditionalException If the date is in an incorrect format.
     * @see AdditionalException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AdditionalException {
        String[] words = fullCommand.split(" ");
        if (words.length != 2) {
            throw new AdditionalException("Please input the date in the correct format and only the date");
        }
        LocalDate date = LocalDate.parse(words[1]);
        ArrayList<Task> listOfTasks = tasks.getListOfSameDates(date);
        ui.showList(listOfTasks);
    }

    /**
     * This is the isBye method that returns whether the command is "bye".
     *
     * @return False because the command is "date".
     */
    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) throws AdditionalException {
    }
}
