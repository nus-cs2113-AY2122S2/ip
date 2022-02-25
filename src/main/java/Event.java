/**
 * Represents an <code>Event</code> object on a person's list of tasks that they must complete.
 * This class inherits from the Task class and supports a slightly different toString() method
 * that contains a marker for the type of Task the Event object is (an "E" for "Event"). Also
 * allows users to specify the time that the Event is occurring.
 */
public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}