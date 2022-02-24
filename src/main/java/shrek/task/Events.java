package shrek.task;


/**
 * Represents an Event task.
 */
public class Events extends Task {
    protected String eventOccurAt;

    /**
     * Initialises the Event task.
     *
     * @param description  Task that the User has inputted.
     * @param eventOccurAt Occurrence date specified by the user.
     */
    public Events(String description, String eventOccurAt) {
        super(description);
        setTaskName("E");
        this.eventOccurAt = eventOccurAt;
    }

    public String getEventOccurAt() {
        return eventOccurAt;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + eventOccurAt + ")";
    }
}
