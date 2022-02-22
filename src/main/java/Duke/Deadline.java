package Duke;

import java.sql.Time;
import java.time.LocalDateTime;

public class Deadline extends Task {
    protected String by;
    protected TimeHandler dateTime;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.dateTime = new TimeHandler(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by + ")\n";
    }
}
