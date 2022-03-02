package duke.task;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 * An event object corresponds to a specific event task that has a task name, the task's symbol and a time range.
 */
public class Events extends Task {
    private final String EVENTS_SYMBOL = "[E]";
    private String time;

    public Events(String taskName, String time) {
        super(taskName);
        setTime(time);
    }

    public String getTaskInformation() {
        return EVENTS_SYMBOL + super.getTaskInformation() + String.format(" (at: %s)", getTime());
    }

    public String getTaskDescription() {
        return super.getTaskDescription() + String.format(" (at: %s)", getTime());
    }

    /**
     * Returns an acknowledgement message which informs the user that the task was successfully added.
     *
     * @return An acknowledgement message containing the name, status, type as well as date and time of the task that
     * was added.
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
     * @return An acknowledgement message containing the name, status, type as well as date and time of the task
     * that was removed.
     */
    public String removeTaskMessage() {
        String acknowledgementMessage = super.removeTaskMessage() + "\t   " + getTaskInformation();
        return acknowledgementMessage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
