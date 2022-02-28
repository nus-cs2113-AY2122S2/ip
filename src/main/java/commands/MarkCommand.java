package commands;

import data.Task;
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
        taskManager.markTask(idx);
        ui.showMarkedCommand(idx);
        try {
            fileManager.saveData(taskManager.getAllTasks());
        } catch (IOException e) {
            System.out.println("\t Error: Failed to save data.");
        }
    }
}
