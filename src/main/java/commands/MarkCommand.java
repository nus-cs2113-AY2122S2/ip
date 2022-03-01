package commands;

import common.DukeException;
import data.TaskManager;
import storage.FileManager;
import ui.Ui;

import java.io.IOException;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskManager taskManager, FileManager fileManager, Ui ui) {
        try {
            taskManager.markTask(idx);
            ui.showMarkedTask(idx);
            fileManager.saveData(taskManager.getAllTasks());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError(e.getMessage());
            System.exit(0);
        }
    }
}
