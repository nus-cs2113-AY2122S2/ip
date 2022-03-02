package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = LocalDate.parse(by);
    }

    // Print Deadline in a fixed format
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                       + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH)) + ")";
    }
}