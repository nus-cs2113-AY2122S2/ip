package serene.task;

public class Event extends Task {
    protected String at;

    /**
     * Constructs a new Event with provided description and time.
     *
     * @param description The event's description
     * @param at Time to attend the event at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
