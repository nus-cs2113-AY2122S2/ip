/**
 * Represents a wish task. This wish is called "Event"
 * tasks that start at a specific time and ends at a
 * specific time.
 */
public class Event extends Task {
    protected String eventAt;

    public Event(String description, String eventAt) {
        super(description);
        this.eventAt = eventAt;
    }

    /**
     * @return indication of wish task for the file.
     */
    @Override
    public String toFileString() {
        return "E " + super.toFileString() + " | " + eventAt;
    }

    /**
     * @return indication of wish task for the user interface.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventAt + ")";
    }
}
