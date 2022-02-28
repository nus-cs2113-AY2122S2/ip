package duke.task;


/**
 * Represents a task of type "Event". An <code>Event</code> object corresponds to
 * an event represented by an event description and the event time period.  e.g., <code>meeting,6pm</code>
 */
public class Event extends DynamicTask {
    public Event(String description, String eventTime) {
        super(description, "E", eventTime);
    }

    public void setEventTime(String eventTime) {
        this.setTime(eventTime);
    }

    public String getEventTime() {
        return this.getTime();
    }


    // overriding method toString in Object class.
    public String toString() {
        return super.toString() + " (at: " + this.getTime() + ")";
    }
}