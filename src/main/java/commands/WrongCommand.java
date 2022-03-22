package commands;

import taskmanager.TaskManager;
import storage.FileEditor;
import ui.Ui;

/**
 * Represents that the user's input cannot be recognised
 */
public class WrongCommand extends Command {

    public WrongCommand() {
    }

    /**
     * Displays a message to inform the user that he/she has input an unrecognisable command
     *
     * @param taskManager Manages the user's task list
     * @param fileEditor Reads and writes from and to the user's task list file in the user's hard disk
     */
    @Override
    public void execute(TaskManager taskManager, FileEditor fileEditor) {
        System.out.println(Ui.WRONG_INPUT_MESSAGE);
    }
}

