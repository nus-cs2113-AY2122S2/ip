package duke.task;

public class Deadline extends Task {
    protected String by;
    protected final String type = "D";

    public Deadline(String description, String by) {
        super(description.trim());
        this.by = by;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}