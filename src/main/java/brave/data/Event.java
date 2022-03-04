package brave.data;

/**
 * Inherit from the Task class
 * Represent an event task with description and the happening date and time of it.
 */
public class Event extends Task {
    private String eventTime;

    /**
     * Class constructor.
     *
     * @param description description of the event task
     * @param eventTime happening date and time of the event task
     */
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public String getDescription() {
        return super.getDescription() + String.format(" (at: %s)", this.eventTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }

    @Override
    public String getSaveFormat() {
        if (!isDone) {
            return "E , 0 , " + this.description + " , " + this.eventTime;
        } else {
            return "E , 1 , " + this.description + " , " + this.eventTime;
        }
    }
}