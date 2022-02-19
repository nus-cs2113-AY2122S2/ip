package duke.task;

public class Deadline extends Task {
    protected String dueBy;

    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    @Override
    public String formatAsData(String FS) {
        return "D" + FS + super.formatAsData(FS) + FS + this.dueBy;
    }

    @Override
    public String toString() {
        return "  DUE: " + super.toString() + " (by: " + this.dueBy + ")";
    }
}
