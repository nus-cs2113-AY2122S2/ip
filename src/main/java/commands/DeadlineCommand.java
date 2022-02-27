package commands;

import data.Deadline;
import data.TaskManager;
import storage.FileManager;
import ui.Ui;

import java.io.IOException;

public class DeadlineCommand extends Command{
    public static final String COMMAND_WORD = "deadline";
    private Deadline toAdd;

    public DeadlineCommand(String description, String by) {
        toAdd = new Deadline(description, by);
    }

    public void execute(TaskManager taskManager, FileManager fileManager, Ui ui) {
        taskManager.addTask(toAdd);
        try {
            taskManager.saveTasks();
        } catch (IOException e) {
            System.out.println("\t Error: Failed to save data.");
        }
        ui.showNewTask(toAdd);
    }
}
