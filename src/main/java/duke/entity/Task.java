package duke.entity;

/**
 * Represents a user task.
 */
public class Task {
    protected String description;
    protected boolean isTaskDone;
    protected int taskId;

    private static int taskRunningCount = 1;

    /**
     * Creates Task and mark as not done.
     *
     * @param description Task description
     */
    public Task(String description) {
        this.taskId = taskRunningCount;
        this.description = description;
        this.isTaskDone = false; //default when a task is created it is not done
        taskRunningCount++;
    }

    /**
     * Creates Task and mark as done according to input.
     *
     * @param description Task description
     * @param isTaskDone Task Completion Status
     */
    public Task(String description, boolean isTaskDone) {
        this.taskId = taskRunningCount;
        this.description = description;
        this.isTaskDone = isTaskDone; //create task based on user inputted boolean, not default
        taskRunningCount++;
    }

    /**
     * Mark Task as done.
     */
    public void markAsDone() {
        this.isTaskDone = true;
        System.out.println("Olivia thanks you for completing the task! :)");
    }

    /**
     * Mark Task as not done.
     */
    public void markAsNotDone() {
        this.isTaskDone = false;
        System.out.println("Hey!!! Please get the task done.");
    }

    /**
     * Returns Description of event.
     * @return Task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns Completion status of task as a printable string.
     * @return Task Completion Status
     */
    public String getTaskStatus() {
        return (isTaskDone ? "[X] " : "[ ] "); //if task done mark it with X
    }

    /**
     * Returns whether a task is complete or not
     * @return Task Status
     */
    public boolean getIsTaskDone() {
        return isTaskDone;
    }

    /**
     * Returns Description of Task with status.
     * @return String of Task description and completion status.
     */
    @Override
    public String toString() {
        return this.getTaskStatus() + this.getDescription();
    }
}


