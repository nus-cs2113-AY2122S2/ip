package solana.task;

public class Deadline extends Task {
    protected String byDate;

    public Deadline(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[D][X] " + description + " (By: " + byDate + ")";
        } else {
            return "[D][ ] " + description + " (By: " + byDate + ")";
        }
    }
}
