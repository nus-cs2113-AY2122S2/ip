package Components;

import Functions.DateTimeHandler;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime dateTime;

    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = DateTimeHandler.dateTimeParse(dateTime);
    }

    public String getDateTime() {
        return DateTimeHandler.toString(dateTime);
    }

    public void setDateTime(String dateTime) {
        this.dateTime = DateTimeHandler.dateTimeParse(dateTime);
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDateTime() + ")";
    }
}
