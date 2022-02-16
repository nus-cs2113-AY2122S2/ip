package duke.commands;

import duke.exceptions.InvalidArgumentException;
import duke.tasks.Task;

import java.util.ArrayList;

public abstract class Command {
    // The task list to act on. May or may not be used.
    protected ArrayList<Task> taskList;

    /**
     * Classes should override and implement this as needed, namely those that requires additional user arguments
     *
     * @throws Exception any exception that may occur while parsing, differs for different commands
     */
    protected void assertArguments() throws InvalidArgumentException {

    }

    /**
     * Setter for taskList
     * @param taskList to set
     */
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * A command must execute an action.
     *
     * @throws Exception any exception that may happen while executing.
     */
    public abstract void execute();
}
