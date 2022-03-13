package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the mark command which is to be executed.
 */
public class MarkCommand extends Command {

    private String fullCommand;

    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Runs when there is a mark command by identify the task to be marked and proceeds to mark it as done.
     * It will then print the confirmation for marking the task and updates the file.
     *
     * @param tasks The TaskList object that contains the list of tasks.
     * @param ui The user interface object that allows for printing of the confirmation message.
     * @param storage The storage object which allows for the saving of the tasks to the file.
     * @throws IOException If there is an error saving tasks to the file.
     * @see IOException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, AdditionalException {
        int indexToMark = Parser.getIndex(fullCommand);
        Task taskToMark = tasks.getTask(indexToMark);
        taskToMark.setDone();
        ui.showMarkCompleted(taskToMark);
        storage.saveAll(tasks);
    }

    /**
     * Marks the task that is just added from the file as done.
     *
     * @param listOfTasks The list of tasks where the last task is to be marked as done.
     */
    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) {
        Task task = listOfTasks.get(listOfTasks.size() - 1);
        task.setDone();
    }
}
