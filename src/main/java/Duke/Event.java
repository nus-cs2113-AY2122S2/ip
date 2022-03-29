package Duke;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    protected TimeHandler dateTime;

    public Event (String taskName, String at) throws DukeExceptionTiming{
        super(taskName);
        this.at = at;
        this.dateTime = new TimeHandler(at);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (at: " +
                formatter.format(dateTime.getLocalDateTime()) + ")\n";
    }
}
