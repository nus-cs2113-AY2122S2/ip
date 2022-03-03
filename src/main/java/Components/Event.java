package Components;

/**
 * Contains an event and a date and time for the event.
 */
public class Event extends Task {
    private String dateTime;

    /**
     * Creates a <code>Event</code> object with specified event description and the date and time for the event.
     *
     * @param description Description of the event.
     * @param dateTime Date and time of the event. String input.
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * @return Date and time of the event.
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Returns event information.
     *
     * @return String containing event information.
     */
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDateTime() + ")";
    }
}
