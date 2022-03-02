/**
 * Represents a wish task. This wish is called "Event"
 * tasks that start at a specific time and ends at a
 * specific time.
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * @return indication of wish task for the file.
     */
    @Override
    public String toFileString() {
        return "E " + super.toFileString() + " | " + at;
    }

    /**
     * @return indication of wish task for the user interface.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
