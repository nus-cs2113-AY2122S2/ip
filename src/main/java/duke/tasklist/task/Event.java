package duke.tasklist.task;

/**
 * The Event object.
 * A subclass of Task.
 */
public class Event extends Task {
    private String at;

    /**
     * Initializes a new Event object.
     *
     * @param description Event description.
     * @param at Event time.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the time of an Event object.
     *
     * @return at Event time.
     */
    public String getAtTime() {
        return at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}
