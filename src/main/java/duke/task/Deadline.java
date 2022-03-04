package duke.task;

/**
 * Represents a task that needs to be done before a specific date/time
 * A Deadline object corresponds to a specific task with a deadline, it contains the name of the task, the task's
 * symbol as well as the task's deadline.
 */
public class Deadline extends Task {
    private final String DEADLINE_SYMBOL = "[D]";
    private String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        setBy(by);
    }

    public String getTaskInformation() {
        return DEADLINE_SYMBOL + super.getTaskInformation() + " (by: " + getBy() + ")";
    }

    public String getTaskDescription() {
        return super.getTaskDescription() + " (by: " + getBy() + ")";
    }

    /**
     * Returns an acknowledgement message which informs the user that the task was successfully added.
     *
     * @return An acknowledgement message containing the name, status, type as well as deadline of the task that was
     * added.
     */
    public String addTaskMessage() {
        String message;
        message = super.addTaskMessage() + "\t   " + getTaskInformation();
        return message;
    }

    /**
     * Returns an acknowledgement message which informs the user that the task status was successfully updated.
     * The task's status refers to whether or not a task is done.
     *
     * @return An acknowledgement message containing the updated details of the task that was updated.
     */
    public String getTaskUpdatedMessage() {
        String message;
        message = super.getTaskUpdatedMessage() + "\t   " + getTaskInformation();
        return message;
    }

    /**
     * Returns an acknowledgement message which informs the user that the task was successfully removed.
     *
     * @return An acknowledgement message containing the name, status, type as well as deadline of the task that was
     * removed.
     */
    public String removeTaskMessage() {
        String acknowledgementMessage = super.removeTaskMessage() + "\t   " + getTaskInformation();
        return acknowledgementMessage;
    }


    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

}
