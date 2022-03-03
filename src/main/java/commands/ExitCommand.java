package commands;

import taskmanager.TaskManager;
import storage.FileEditor;
import ui.Ui;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public ExitCommand() {
        isExit = true;
    }

    @Override
    public void execute(TaskManager taskManager, FileEditor fileEditor) {
        Ui.showExitMessage();
    }
}
