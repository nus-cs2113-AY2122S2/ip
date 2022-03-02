package commands;

import common.DukeException;
import data.TaskManager;
import storage.FileManager;
import ui.Ui;

import java.io.IOException;

/**
 * Command to unmark a task as not done using the last displayed index.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskManager taskManager, FileManager fileManager, Ui ui) {
        try {
            taskManager.unmarkTask(idx);
            ui.showUnmarkedTask(idx);
            fileManager.saveData(taskManager.getAllTasks());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            System.out.println("\t Error: Failed to save data.");
        }

    }
}
