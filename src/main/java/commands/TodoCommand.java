package commands;

import data.TaskManager;
import data.Todo;
import storage.FileManager;
import ui.Ui;

import java.io.IOException;

/**
 * Command to add a todo task.
 */
public class TodoCommand extends Command{
    public static final String COMMAND_WORD = "todo";
    private Todo toAdd;

    public TodoCommand(String description) {
        toAdd = new Todo(description);
        isExit = false;
    }

    @Override
    public void execute(TaskManager taskManager, FileManager fileManager, Ui ui) {
        taskManager.addTask(toAdd);
        try {
            fileManager.saveData(taskManager.getAllTasks());
        } catch (IOException e) {
            System.out.println("\t Error: Failed to save data.");
        }
        ui.showNewTask(toAdd);
    }
}
