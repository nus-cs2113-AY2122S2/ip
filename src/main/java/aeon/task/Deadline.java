package aeon.task;
import aeon.task.Task;

/**
 * Represents a deadline task which needs to include a deadline itself delimited by the keyword '/by'
 */
public class Deadline extends Task {

    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")\n";
    }
}