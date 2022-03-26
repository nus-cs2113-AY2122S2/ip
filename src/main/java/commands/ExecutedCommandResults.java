package commands;

import tasks.Task;
import tasks.TaskList;

/**
 * This class handles the information created from execution
 * of a command and passes it to the Ui class for printing.
 */
public class ExecutedCommandResults {
    private final String commandMessage;
    private TaskList tasks = null;
    private Task task = null;

    /**
     * Creates an instance with the message and a TaskList instance.
     *
     * @param commandMessage Relevant output message
     * @param tasks Relevant TaskList instance
     */
    public ExecutedCommandResults(String commandMessage, TaskList tasks) {
        this.commandMessage = commandMessage;
        this.tasks = tasks;
    }

    /**
     * Creates an instance with the message and a Task instance.
     *
     * @param commandMessage Relevant output message
     * @param task Relevant Task instance
     */
    public ExecutedCommandResults(String commandMessage, Task task) {
        this.commandMessage = commandMessage;
        this.task = task;
    }

    /**
     * Creates an instance with only the message.
     *
     * @param commandMessage Relevant output message
     */
    public ExecutedCommandResults(String commandMessage) {
        this.commandMessage = commandMessage;
    }

    /**
     * Returns the command message.
     *
     * @return commandMessage
     */
    public String getCommandMessage() {
        return commandMessage;
    }

    /**
     * Returns the TaskList instance, if any.
     *
     * @return tasks
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Returns the Task instance, if any.
     *
     * @return task
     */
    public Task getTask() {
        return task;
    }
}
