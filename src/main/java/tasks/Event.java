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
    public String at;

    /**
     * Creates a new Event object.
     *
     * @param event Event description.
     * @param at    When the event will take place.
     */
    public Event(String event, String at) throws IllegalArgumentException {
        super("E", event);
        if (at == null) {
            throw new IllegalArgumentException(String.format("No `%s` argument specified!", REQ_ARG));
        }
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.at);
    }
}
