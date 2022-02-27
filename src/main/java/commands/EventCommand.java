package commands;

import data.Event;
import data.TaskManager;
import storage.FileManager;
import ui.Ui;

import java.io.IOException;

public class EventCommand extends Command{
    public static final String COMMAND_WORD = "event";
    private Event toAdd;

    public EventCommand(String description, String at) {
        toAdd = new Event(description, at);
        isExit = false;
    }

    @Override
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
