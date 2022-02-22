package bim.command;

import bim.task.TaskList;

public abstract class Command {
    private TaskList tasks;
    private int index;
    private String ERROR_INVALID_COMMAND = "I did not understand that!";

    public Command(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    public Command() {    }

    public void run() throws UnsupportedOperationException {
        throw new UnsupportedOperationException(ERROR_INVALID_COMMAND);
    }
}
