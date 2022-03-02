/**
 * Represents a wish task. This wish is called "Deadline"
 * tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * @return indication of wish task for the file.
     */
    @Override
    public String toFileString() {
        return "D " + super.toFileString() + " | " + by;
    }

    /**
     * @return indication of wish task for the user interface.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
