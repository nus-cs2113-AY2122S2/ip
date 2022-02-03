package tasks;

/**
 * Event class is a specialisation of the Task class that tracks
 * tasks that start and end at a specific time.
 */
public final class Event extends Task {
    /**
     * Specifies the required argument to create an Event.
     */
    public static String REQ_ARG = "at";

    /**
     * When the event will take place.
     */
    public String occursAt;

    /**
     * Creates a new Event object.
     *
     * @param event Event description.
     * @param occursAt    When the event will take place.
     */
    public Event(String event, String occursAt) throws IllegalArgumentException {
        super("E", event);
        if (occursAt == null) {
            throw new IllegalArgumentException(String.format("No `%s` argument specified!", REQ_ARG));
        }
        this.occursAt = occursAt;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.occursAt);
    }
}
