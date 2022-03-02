import java.util.ArrayList;

/**
 * User decides to end the program.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        isExit = true;
    }

    /**
     * execute print exit message to inform user program has come
     * to an end.
     *
     * @param taskList    contains all wish task.
     * @param uiManager   assist in printing exit message.
     * @param fileManager manages the file.
     */
    public void execute(ArrayList<Task> taskList, UiManager uiManager, TaskFileManager fileManager) {
        uiManager.printExitMessage();
    }
}
