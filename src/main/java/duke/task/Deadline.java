package duke.task;
public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String trim, boolean equals, String trim1) {
        super();
    }

    public String getTask() {
        return "[D]" + super.getTask() + " (by: " + by + ")";
    }
}

