public class Deadline extends Task {
    protected String by;
    protected static final String DEADLINE_SYMBOL = "D";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "["+DEADLINE_SYMBOL+"]" + super.toString() + " (by: " + by + ")";
    }
}
