import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a Task object on a person's list of tasks that they must complete.
 * A <code>Task</code> corresponds to a Task represented by a description of the
 * task as well as a flag for whether it has been completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {

        return "[" + this.getStatusIcon() + "] " + description;
    }



}