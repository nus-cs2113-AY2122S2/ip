package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.AdditionalException;
import duke.storage.Storage;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the delete command which is to be executed.
 */
public class DeleteCommand extends Command {

    private String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * This is the execute method that runs when there is a delete command.
     * The method will identify the task to be deleted and proceeds to delete it.
     * It will then print the confirmation for deleting the task and updates the file.
     *
     * @param tasks The TaskList object that contains the list of tasks.
     * @param ui The user interface object that allows for printing of the confirmation message.
     * @param storage The storage object which allows for the saving of the tasks to the file.
     * @throws IOException If there is an error saving tasks to the file.
     * @see IOException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, AdditionalException {
        String[] words = fullCommand.split(" ");
        if (words.length != 2) {
            throw new AdditionalException("Please input the index and only the index");
        }
        int indexToDelete = Integer.parseInt(words[1]) - 1;
        Task taskToDelete = tasks.getTask(indexToDelete);
        tasks.deleteTask(indexToDelete);
        int numberOfTasks = tasks.getSize();
        ui.showDeleteDone(taskToDelete, numberOfTasks);
        storage.saveAll(tasks);
    }

    /**
     * This is the isBye method that returns whether the command is "bye".
     *
     * @return False because the command is "delete".
     */
    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) {

    }
}
