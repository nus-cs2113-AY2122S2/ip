package duke.tasklist;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        // return "[D]" + super.toString() + "(" + by + ")";
        return "[D]" + super.toString() + "(" + by + ")" ; // mark done task with X

     }
}