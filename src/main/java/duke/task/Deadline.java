package duke.task;
public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getTask() {
        return "[D]" + super.getTask() + " (by: " + by + ")";
    }
}

