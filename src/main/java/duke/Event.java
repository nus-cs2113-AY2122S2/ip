package duke;

/**
 * Represents a Task of type Event.
 * Event tasks contain a description of the task as well as the time of the event that will occur.
 */
public class Event extends Task{
    protected String eventTime;

    /**
     * @param title The title of the Event to be created.
     * @param eventTime String containing the event time.
     */
    public Event(String title, String eventTime) {
        super(title);
        this.eventTime = eventTime;
    }

    /**
     * Returns a string that contains the event period.
     * @return
     */
    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * Returns a string representation of the Event object and its attributes.
     * @return String containing the event and its attributes.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getEventTime() + ")";
    }
}
