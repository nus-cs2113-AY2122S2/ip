package duke.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, int taskUniqueID, String by) {
        super(description, taskUniqueID);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}