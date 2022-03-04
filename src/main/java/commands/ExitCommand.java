package commands;

import taskmanager.TaskManager;
import storage.FileEditor;
import ui.Ui;

/**
 * Represents that the user has input a command to terminate the programme
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Initialises the command as an exit command
     */
    public ExitCommand() {
        isExit = true;
    }

    /**
     * Displays a message to bid goodbye to the user
     *
     * @param taskManager Manages the user's task list
     * @param fileEditor Reads and writes from and to the user's task list file in the user's hard disk
     */
    @Override
    public void execute(TaskManager taskManager, FileEditor fileEditor) {
        Ui.showExitMessage();
    }
}
