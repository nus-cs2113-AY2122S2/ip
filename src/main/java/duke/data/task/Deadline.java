package duke.data.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static duke.common.Strings.FORMAT_DATE;
import static duke.common.Strings.FORMAT_DATETIME;

public class Deadline extends Task {
    protected String dueBy;

    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy;
        try {
            savedDate = LocalDateTime.parse(dueBy, DateTimeFormatter.ofPattern(FORMAT_DATETIME)).toLocalDate();
        } catch (DateTimeParseException e) {
            try {
                savedDate = LocalDate.parse(dueBy, DateTimeFormatter.ofPattern(FORMAT_DATE));
            } catch (DateTimeParseException f) {
                savedDate = null;
            }
        }
    }

    @Override
    public String formatAsData(String FS) {
        return "D" + FS + super.formatAsData(FS) + FS + this.dueBy;
    }

    @Override
    public String toString() {
        if (savedDate != null) {
            return "  DUE: " + super.toString() + " (by: " + this.dueBy + ")^";
        }
        return "  DUE: " + super.toString() + " (by: " + this.dueBy + ")";
    }
}
