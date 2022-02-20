package duke.command;

import duke.data.TaskList;

import java.util.ArrayList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected TaskList taskList = null;
    protected ArrayList<String> commandFeedback;

    protected Command() {
        this.commandFeedback = new ArrayList<>();
    }

    /**
     * Supplies the task list that the command operates on.
     */
    public void setTaskList(TaskList t) {
        taskList = t;
    }

    /**
     * Executes command logic and returns feedback.
     * @return command feedback. Each element of the ArrayList represents one line of feedback.
     */
    public abstract ArrayList<String> execute();
}
