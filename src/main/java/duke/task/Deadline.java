package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Example: deadline return book /by June 6th
 * description: "return book"
 * by: "June 6th"
 */
public class Deadline extends Task {
    protected String by;
    protected String preposition;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description.trim());
        date = null;
        by = by.trim();
        try {
            date = LocalDate.parse(by);
            this.by = by;
        } catch (DateTimeParseException e) {
            this.by = by;
        }
        this.preposition = "by";
    }

    public String getTiming() {
        return this.by;
    }

    public String getPrepositions() {
        return "/" + this.preposition;
    }

    @Override
    public String toString() {
        if (date != null) {
            return String.format("[D]%s (%s: %s)",
                    super.toString(),
                    preposition,
                    date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        }
        return String.format("[D]%s (%s: %s)", super.toString(), preposition, by);
    }

}
