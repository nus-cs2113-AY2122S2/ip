package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Example: event company meeting /at 2-4pm, June 8th
 * description: "company meeting"
 * by: "2-4pm, June 8th"
 */
public class Event extends Task {
    protected String at;
    protected String preposition;
    protected LocalDate date;

    public Event(String description, String at) {
        super(description.trim());
        date = null;
        at = at.trim();
        try {
            date = LocalDate.parse(at);
            this.at = at;
        } catch (DateTimeParseException e) {
            this.at = at;
        }
        this.preposition = "at";
    }

    public String getTiming() {
        return this.at;
    }

    public String getPrepositions() {
        return "/" + this.preposition;
    }

    @Override
    public String toString() {
        if (date != null) {
            return String.format("[E]%s (%s: %s)",
                    super.toString(),
                    preposition,
                    date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        }
        return String.format("[E]%s (%s: %s)", super.toString(), preposition, at);
    }
}
