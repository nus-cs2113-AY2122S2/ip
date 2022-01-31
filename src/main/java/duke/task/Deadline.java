package duke.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getTask() {
        return "[D]" + super.getTask() + " (by: " + by + ")";
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + this.by;
    }
}
