package commands;

import taskmanager.TaskManager;
import storage.FileEditor;
import ui.Ui;

public class WrongCommand extends Command {

    public WrongCommand() {
    }

    @Override
    public void execute(TaskManager taskManager, FileEditor fileEditor) {
        System.out.println(Ui.WRONG_INPUT_MESSAGE);
    }
}

