package duke.task;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }

    @Override
    public String saveString() {
        return "D" + super.saveString() + String.format(" | %s", this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.by);
    }
}
