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
     * This is the getDate method that returns the date of this task.
     *
     * @return The date of deadline of this task.
     */
    public LocalDate getDate() {
        return dateOfDeadline;
    }

    /**
     * This is the toString method that returns a particular format to represent this task.
     *
     * @return The printing format of this task.
     */
    @Override
    public String toString() {
        String deadline = dateOfDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

}
