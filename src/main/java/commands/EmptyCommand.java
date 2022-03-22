package commands;

import taskmanager.TaskManager;
import storage.FileEditor;
import ui.Ui;

/**
 * Represents that the user failed to input a command
 */
public class EmptyCommand extends Command {

    public static final String COMMAND_WORD = "";

    public EmptyCommand() {
    }

    /**
     * Displays a message to inform the user that he/she has failed to input anything
     *
     * @param taskManager Manages the user's task list
     * @param fileEditor Reads and writes from and to the user's task list file in the user's hard disk
     */
    @Override
    public void execute(TaskManager taskManager, FileEditor fileEditor) {
        System.out.println(Ui.EMPTY_INPUT_MESSAGE);
    }
}