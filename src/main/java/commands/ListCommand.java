package commands;

import data.Task;
import data.TaskManager;
import storage.FileManager;
import ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskManager taskManager, FileManager fileManager, Ui ui) {
        ArrayList<Task> tasks = taskManager.getAllTasks();
        ui.listAllTasks(tasks);
    }
}
