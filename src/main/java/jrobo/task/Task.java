package jrobo.task;

/**
 * Task is the parent class of Deadline, Event, Todo.
 *
 * @author Ege Demirkirkan
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
    }

    /**
     * Returns task's status icon.
     *
     * @return "X" or " " according to the done status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the main text of the task.
     *
     * @return description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for isDone field.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Setter for isDone field.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a String object representing the Task
     *
     * @return the String that represents the task in the following format; [{status_icon}]{description}
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + getDescription();
    }
}
