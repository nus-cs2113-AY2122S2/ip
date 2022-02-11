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
     * @param event    Event description.
     * @param isDone   Whether the task is done.
     * @param occursAt When the event will take place.
     */
    public Event(String event, boolean isDone, String occursAt) throws IllegalArgumentException {
        super("E", isDone, event);
        if (occursAt == null) {
            throw new IllegalArgumentException(String.format("No `%s` argument specified!", REQ_ARG));
        }
        this.occursAt = occursAt;
    }

    /**
     * Attempts to unmarshal a storage-friendly parts string into an Event object.
     *
     * @param parts A storage-friendly string split into parts.
     * @return Parsed Deadline object.
     */
    public static Event unMarshal(String[] parts) {
        return new Event(parts[2], Boolean.parseBoolean(parts[1]), parts[3]);
    }

    @Override
    public String marshal() {
        return String.format("%s | %s", super.marshal(), this.occursAt);
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.occursAt);
    }
}
