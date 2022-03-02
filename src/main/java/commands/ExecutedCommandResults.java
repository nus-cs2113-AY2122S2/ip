package commands;

import tasks.Task;
import tasks.TaskList;

public class ExecutedCommandResults {
    private final String commandMessage;
    private TaskList tasks = null;
    private Task task = null;

    public ExecutedCommandResults(String commandMessage, TaskList tasks) {
        this.commandMessage = commandMessage;
        this.tasks = tasks;
    }

    public ExecutedCommandResults(String commandMessage, Task task) {
        this.commandMessage = commandMessage;
        this.task = task;
    }

    public ExecutedCommandResults(String commandMessage) {
        this.commandMessage = commandMessage;
    }

    public String getCommandMessage() {
        return commandMessage;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public Task getTask() {
        return task;
    }
}
