package commands;

import common.DukeException;
import data.Task;
import data.TaskManager;
import storage.FileManager;
import ui.Ui;

import java.io.IOException;

/**
 * Command to delete a task using the last displayed index.
 */
public class DeleteCommand extends Command{
    public static final String COMMAND_WORD = "delete";
    int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskManager taskManager, FileManager fileManager, Ui ui) {
        try {
            Task deletedTask = taskManager.getTask(idx);
            taskManager.deleteTask(idx);
            ui.showRemovedTask(deletedTask);
            fileManager.saveData(taskManager.getAllTasks());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            System.out.println("\t Error: Failed to save data.");
        }
    }
}
