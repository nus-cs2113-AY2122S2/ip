package duke.tasks;
/**
 * Represents an <code>Deadline</code> object on a person's list.
 * This class inherits from the Task class and supports a slightly different toString() method
 * that contains a marker for the Deadline object (an "D" for "Deadline"). Also
 * allows users to set the time/date the Deadline task should be completed by.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String saveTasks() {
        return "D " + super.saveTasks() + " | " + this.by + System.lineSeparator();
    }
}
