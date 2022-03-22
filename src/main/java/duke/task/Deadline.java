package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task (eg. deadline return book /by 2019-10-15)
 */
public class Deadline extends Task {
    public static final String TASK_SHORTFORM = "D";
    private LocalDate deadlineDate; // yyyy-mm-dd

    public Deadline(String description, LocalDate dateInput) {
        this(false, description, dateInput);
    }

    public Deadline(boolean isDone, String description, LocalDate dateInput) {
        super(isDone, description);
        this.deadlineDate = dateInput;
    }

    // Representation of Deadline in the saved file
    @Override
    public String saveString() {
        return "D" + super.saveString() + String.format(" | %s", this.deadlineDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
