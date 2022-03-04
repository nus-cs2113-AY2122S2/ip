package duke.task;

/**
 * Represents an Event task (eg. event project meeting /at 2019-10-15)
 */
public class Event extends Task {
    private String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public Event(boolean isDone, String description, String eventTime) {
        super(isDone, description);
        this.eventTime = eventTime;
    }

    // Representation of Event in the saved file
    @Override
    public String saveString() {
        return "E" + super.saveString() + String.format(" | %s", this.eventTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("(at:%s)", this.eventTime);
    }
}
