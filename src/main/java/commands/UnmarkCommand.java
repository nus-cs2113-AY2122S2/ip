package commands;

import data.TaskManager;
import storage.FileManager;
import ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskManager taskManager, FileManager fileManager, Ui ui) {
        taskManager.unmarkTask(idx);
        ui.showUnmarkedTask(idx);
        try {
            fileManager.saveData(taskManager.getAllTasks());
        } catch (IOException e) {
            System.out.println("\t Error: Failed to save data.");
        }

    }
}
