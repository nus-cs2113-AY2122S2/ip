package Duke;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime at;
    protected boolean hasTime;

    public Event (String taskName, LocalDateTime at, boolean hasTime) {
        super(taskName);
        this.at = at;
        this.hasTime = hasTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                at + ")\n";
    }
}
