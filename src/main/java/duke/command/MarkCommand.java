package duke.command;

import duke.TaskList;
import duke.Ui;
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
     * This is the execute method that runs when there is a mark command.
     * The method will identify the task to be marked and proceeds to mark it as done.
     * It will then print the confirmation for marking the task and updates the file.
     *
     * @param tasks The TaskList object that contains the list of tasks.
     * @param ui The user interface object that allows for printing of the confirmation message.
     * @param storage The storage object which allows for the saving of the tasks to the file.
     * @throws IOException If there is an error saving tasks to the file.
     * @see IOException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] words = fullCommand.split(" ");
        int indexToMark = Integer.parseInt(words[1]) - 1;
        Task taskToMark = tasks.getTask(indexToMark);
        taskToMark.markAsDone();
        ui.showMarkCompleted(taskToMark);
        storage.saveAll(tasks);
    }

    /**
     * This is the isBye method that returns whether the command is "bye".
     *
     * @return False because the command is "mark".
     */
    @Override
    public boolean isBye() {
        return false;
    }

    /**
     * This is the executeFromFile method that takes in the list of tasks.
     * It then marks the last task of the list of tasks to be done.
     *
     * @param listOfTasks The list of tasks where the last task is to be marked as done.
     */
    @Override
    public void executeFromFile(ArrayList<Task> listOfTasks) {
        Task task = listOfTasks.get(listOfTasks.size() - 1);
        task.markAsDone();
    }
}
