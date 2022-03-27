package tasks;

/**
 * A type of Task class
 */
public class Deadline extends Task {

    protected String by;

    /**
     * constructor
     *
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
    }

    /**
     * overwrite the print function
     *
     * @return
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
