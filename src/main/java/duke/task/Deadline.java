package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadlineDate;

    public Deadline(String description, String dateInput) {
        super(description);
        this.deadlineDate = LocalDate.parse(dateInput); // yyyy-mm-dd
    }

    public Deadline(boolean isDone, String description, String dateInput) {
        super(isDone, description);
        this.deadlineDate = LocalDate.parse(dateInput); // yyyy-mm-dd
    }

    @Override
    public String saveString() {
        return "D" + super.saveString() + String.format(" | %s", this.deadlineDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
