package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate eventDate;

    public Event(String description, String dateInput) {
        super(description);
        this.eventDate = LocalDate.parse(dateInput); // yyyy-mm-dd
    }

    public Event(boolean isDone, String description, String dateInput) {
        super(isDone, description);
        this.eventDate = LocalDate.parse(dateInput); // yyyy-mm-dd
    }

    @Override
    public String saveString() {
        return "E" + super.saveString() + String.format(" | %s", this.eventDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", this.eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
