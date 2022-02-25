package Duke;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected TimeHandler dateTime;

    public Deadline(String description, String by) throws DukeExceptionTiming {
        super(description);
        this.by = by;
        this.dateTime = new TimeHandler(by);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " +
                formatter.format(dateTime.getLocalDateTime()) + ")\n";
    }
}
