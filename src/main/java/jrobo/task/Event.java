package jrobo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        try {
            LocalDate date = LocalDate.parse(at.trim());
            this.at = " " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            this.at = at;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
}