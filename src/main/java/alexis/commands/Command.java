package alexis.commands;

import alexis.storage.Storage;
import alexis.taskList.TaskList;

/**
 * User's input is parsed into a command and represented here.
 */
public class Command {
    protected boolean isExit;

    /**
     * Command initialised to be not an exit command
     */
    public Command() {
        isExit = false;
    }

    /**
     * Saves Alexis.tasks into the file in the user's pc
     *
     * @param taskList Alexis.tasks
     * @param storage Alexis.storage
     */
    public void execute(TaskList taskList, Storage storage) {
        storage.save(taskList.getListSize(), taskList.taskArrayList);
    }

    public boolean isExit() {
        return isExit;
    }
}
