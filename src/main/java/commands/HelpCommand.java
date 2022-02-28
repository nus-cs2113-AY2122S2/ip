package commands;

import data.TaskManager;
import storage.FileManager;
import ui.Ui;

public class HelpCommand extends Command{
    public static final String COMMAND_WORD = "help";

    @Override
    public void execute(TaskManager taskManager, FileManager fileManager, Ui ui) {
        ui.showSupportedCommand();
    }
}
