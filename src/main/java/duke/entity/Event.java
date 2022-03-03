package duke.entity;

/**
 * Represents a user task of Event type.
 */
public class Event extends Task {

    private final static String EVENT_MARKER = "[E]";
    protected String duration;
    /**
     * Creates Event and mark as done according to input.
     *
     * @param description Task description
     * @param duration Deadline of Task
     */
    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }
    /**
     * Creates Event and mark as done according to input.
     *
     * @param description Task description
     * @param duration Deadline of Task
     * @param isTaskDone Task Completion Status
     */
    public Event(String description, boolean isTaskDone, String duration) {
        super(description, isTaskDone);
        this.duration = duration;
    }
    /**
     * Returns the duration of event.
     * @return duration of task.
     */
    public String getDuration() {
        return duration;
    }
    /**
     * Returns Description of event with completion status and duration.
     * @return String of event description and status.
     */
    @Override
    public String toString() {
        return EVENT_MARKER + super.toString() + " (at: " + duration + ")";
    }
}