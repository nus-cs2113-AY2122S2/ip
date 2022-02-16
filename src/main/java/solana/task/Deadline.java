package solana.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[D][X] " + description + " (By: " + by + ")";
        } else {
            return "[D][ ] " + description + " (By: " + by + ")";
        }
    }
}
