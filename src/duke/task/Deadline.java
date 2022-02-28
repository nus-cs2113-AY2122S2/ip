package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline object. A Task with a deadline
 */
public class Deadline extends Task {
    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;

    public Deadline(String name, boolean marked, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(name, marked);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Get the deadline in a string format that is more readible for the user
     * @return a string of the deadline that is more readible for the user
     */
    private String getDeadline() {
        String deadline = deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ";
        deadline += deadlineTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        return deadline;
    }

    /**
     * Get a string representation of the deadline in the save format
     * @return string of the deadline in the save format
     */
    private String getDeadlineDetails() {
        return deadlineDate + " | " + deadlineTime;
    }

    /**
     * Converts the Deadline object into string format
     * @return a string representation of the Deadline object
     */
    public String toString() {
        if (super.getMarked()) {
            return " [D][X] " + getName() + " (by: " + getDeadline() + ")";
        }else {
            return " [D][ ] " + getName() + " (by: " + getDeadline() + ")";
        }
    }

    /**
     * Converts the Deadline object into string format for storage
     * @return a string representation of the Deadline object to be stored
     */
    public String getTaskDetails() {
        return "deadline | " + (getMarked() ? 1:0) + " | " + getName() + " | " + getDeadlineDetails() + "\n";
    }
}
