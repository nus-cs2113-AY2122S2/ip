package Duke;

import java.sql.Time;
import java.time.LocalDateTime;

public class Event extends Task {
    protected String at;
    protected TimeHandler dateTime;

    public Event (String taskName, String at) {
        super(taskName);
        this.at = at;
        this.dateTime = new TimeHandler(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                at + ")\n";
    }
}
