package commands;

import data.TaskManager;
import storage.FileManager;
import ui.Ui;

public class ByeCommand extends Command{
    public static final String COMMAND_WORD = "bye";

    public ByeCommand() {
        isExit = true;
    }

    @Override
    public void execute(TaskManager taskManager, FileManager fileManager, Ui ui) {
        ui.showByeMessage();
    }
}
