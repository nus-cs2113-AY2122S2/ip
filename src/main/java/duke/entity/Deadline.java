package duke.entity;

public class Deadline extends Task {

    private final static String DEADLINE_MARKER = "[D]";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isTaskDone, String by) {
        super(description, isTaskDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return DEADLINE_MARKER + super.toString() + " (by: " + by + ")";
    }
}