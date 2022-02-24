package serene.task;

public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline with provided description and time.
     *
     * @param description The deadline's description
     * @param by Time to do the deadline by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
