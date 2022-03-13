package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task{

    private LocalDate dateOfDeadline;

    public Deadline(String description, LocalDate dateOfDeadline, String typeOfTask) {
        super(description, typeOfTask);
        this.dateOfDeadline = dateOfDeadline;
    }

    /**
     * Returns the date of this deadline task.
     *
     * @return The date of deadline of this deadline task.
     */
    public LocalDate getDate() {
        return dateOfDeadline;
    }

    /**
     * Returns a particular format to represent this deadline task.
     *
     * @return The printing format of this deadline task.
     */
    @Override
    public String toString() {
        String deadline = dateOfDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

}
