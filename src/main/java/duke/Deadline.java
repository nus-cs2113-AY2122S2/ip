package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Deadline extends Task{
    protected String by;

    public LocalDateTime getByDate() {
        return byDate;
    }

    public void setByDate(LocalDateTime byDate) {
        this.byDate = byDate;
    }

    public String formatByDate() {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("dd/MM/yyyy[ HH:mm]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();
        return byDate.format(formatter);
    }

    protected LocalDateTime byDate;
    public Deadline(String title, String by) {
        super(title);
        this.by = by;
    }

    public Deadline(String title, LocalDateTime byDate) {
        super(title);
        this.byDate = byDate;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        if (getBy() != null) {
            return "[D]" + super.toString() + " (by: " + getBy() + ")";
        }
        else {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern("dd/MM/yyyy[ HH:mm]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter();
            return "[D]" + super.toString() + " (by: " + formatByDate() + ")";
        }
    }
}
