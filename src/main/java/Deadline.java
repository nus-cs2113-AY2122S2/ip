/**
 * Represents a wish task. This wish is called "Deadline"
 * tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected String deadlineBy;

    public Deadline(String description, String deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }

    /**
     * @return indication of wish task for the file.
     */
    @Override
    public String toFileString() {
        return "D " + super.toFileString() + " | " + deadlineBy;
    }

    /**
     * @return indication of wish task for the user interface.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineBy + ")";
    }
}
