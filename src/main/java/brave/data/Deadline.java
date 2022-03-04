package brave.data;

/**
 * Inherit from Task class, with additional "by" as the deadline
 */
public class Deadline extends Task {
    private String by;

    /**
     * Class constructor
     * @param description description for the given task
     * @param by indicating the deadline of given task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getDescription() {
        return super.getDescription() + String.format(" (by: %s)", this.by);
    }

    /**
     * Override print method
     * @return String that is correctly formatted
     */
    @Override
    public String toString() {
        return "[D]" + super.toString();
    }

    @Override
    public String getSaveFormat() {
        if (!isDone) {
            return "D , 0 , " + this.description + " , " + this.by;
        } else {
            return "D , 1 , " + this.description + " , " + this.by;
        }
    }
}