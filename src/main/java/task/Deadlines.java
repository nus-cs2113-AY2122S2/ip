package task;

public class Deadlines extends Task {
    protected String by;

    public Deadlines(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
        isDone = false;

    }

    public String getBy() {
        return by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)\n", this.getType(), this.getStatusIcon(), this.getDescription(), this.getBy());
    }
}
