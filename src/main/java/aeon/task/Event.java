package aeon.task;

/**
 * Represents an upcoming event, with a targeted future date using the delimiter '/at'
 */
public class Event extends Task {

    protected String eventDate;
    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + eventDate + ")\n";
    }
}
