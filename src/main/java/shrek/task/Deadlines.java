package shrek.task;

/**
 * Represents a Deadline task.
 */
public class Deadlines extends Task {
    protected String taskDueBy;

    /**
     * Initialises the deadline task.
     *
     * @param description Task that the User has inputted.
     * @param taskDueBy   Due date specified by user.
     */
    public Deadlines(String description, String taskDueBy) {
        super(description);
        setTaskName("D");
        this.taskDueBy = taskDueBy;
    }

    public String getTaskDueBy() {
        return taskDueBy;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + taskDueBy + ")";
    }
}
