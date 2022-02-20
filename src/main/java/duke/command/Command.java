package duke.command;

import duke.data.TaskList;

import java.util.ArrayList;

public abstract class Command {
    protected TaskList taskList = null;
    protected ArrayList<String> commandFeedback;

    protected Command() {
        this.commandFeedback = new ArrayList<>();
    }

    public void setTaskList(TaskList t) {
        taskList = t;
    }

    public abstract ArrayList<String> execute();
}
