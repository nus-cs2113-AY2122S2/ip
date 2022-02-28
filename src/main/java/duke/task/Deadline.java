package duke.task;

/**
 * Represents a task of type "Deadline". A <code>Deadline</code> object corresponds to
 * a deadline represented by a deadline description and the due date of the deadline.
 * e.g., <code>homework,7pm Monday</code>
 */
public class Deadline extends DynamicTask {

    public Deadline(String description, String dueDate) {
        super(description, "D", dueDate);
    }

    public void setDueDate(String dueDate) {
        this.setTime(dueDate);
    }

    public String getDueDate() {
        return this.getTime();
    }

    // overriding method toString in Object class
    public String toString() {
        return super.toString() + " (by: " + this.getTime() + ")";
    }
}
