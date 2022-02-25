/**
 * Represents an <code>Deadline</code> object on a person's list of tasks that they must complete.
 * This class inherits from the Task class and supports a slightly different toString() method
 * that contains a marker for the type of Task the Deadline object is (an "D" for "Deadline"). Also
 * allows users to specify the time/date the Deadline task should be completed by.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}