package solana.task;

/**
 * Represents the Event task. Event tasks are tasks that start and end at a specific time.
 */
public class Event extends Task {
    protected String atDate;

    public Event(String description, String atDate) {
        super(description);
        this.atDate = atDate;
    }

    /**
     * Returns the Event class as a string format.
     *
     * @return String format.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[E][X] " + description + " (At: " + atDate + ")";
        } else {
            return "[E][ ] " + description + " (At: " + atDate + ")";
        }
    }
}
