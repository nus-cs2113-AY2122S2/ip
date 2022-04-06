/**
 * Subclass of Task
 * Deadline has an additional parameter 'by' that indicates the latest time the task should be completed by
 * The string "/by" in the user input will demarcate the deadline timing
 * User input to add a new Deadline is:
 * > deadline [description] /by [time]
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

