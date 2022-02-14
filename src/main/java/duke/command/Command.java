package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the commands a user can execute
 */
public abstract class Command {
    private boolean isExit;

    public Command() {
        this.isExit = false;
    }

    /**
     * Setter for the exit flag
     * 
     * @param isExit
     *            Boolean for whether to exit the app
     */
    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Getter for the exit flag
     * 
     * @return boolean of the exit flag
     */
    public boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Abstract method to execute command
     * 
     * @param tasks
     *            TaskList in the app
     * @param ui
     *            Ui in the app
     * @param storage
     *            Storage in the app
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
