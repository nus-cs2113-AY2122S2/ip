package duke.task;

import duke.DukeException;
import duke.command.DeadlineCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task (eg. event project meeting /at 2019-10-15)
 */
public class Event extends Task {
    public static final String TASK_SHORTFORM = "E";
    private LocalDate eventDate; // yyyy-mm-dd

    public Event(String description, LocalDate dateInput) {
        this(false, description, dateInput);
    }

    public Event(boolean isDone, String description, LocalDate dateInput) {
        super(isDone, description);
        this.eventDate = dateInput;
    }

    // Representation of Event in the saved file
    @Override
    public String saveString() {
        return "E" + super.saveString() + String.format(" | %s", this.eventDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", this.eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
