package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime timing;
    protected static final String EVENT_SYMBOL = "E";

    public Event(String description, LocalDateTime timing) {
        super(description);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "["+EVENT_SYMBOL+"]" + super.toString() +
                " (at: " + timing.format(DateTimeFormatter.ofPattern("MMM d yyyy kka")) + ")";
    }
}
