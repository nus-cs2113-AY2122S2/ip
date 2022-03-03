package commands;

import taskmanager.TaskManager;
import storage.FileEditor;
import ui.Ui;

public class EmptyCommand extends Command {

    public static final String COMMAND_WORD = "";

    public EmptyCommand() {
    }

    @Override
    public void execute(TaskManager taskManager, FileEditor fileEditor) {
        System.out.println(Ui.EMPTY_INPUT_MESSAGE);
    }
}