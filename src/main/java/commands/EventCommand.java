package commands;

import data.Event;
import data.TaskManager;
import storage.FileManager;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

public class EventCommand extends Command{
    public static final String COMMAND_WORD = "event";
    private Event toAdd;

    public EventCommand(String description, LocalDateTime at) {
        toAdd = new Event(description, at);
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
