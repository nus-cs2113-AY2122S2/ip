package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    protected static final String DEADLINE_SYMBOL = "D";

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "["+DEADLINE_SYMBOL+"]" + super.toString() +
                " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy kka")) + ")";
    }
}
