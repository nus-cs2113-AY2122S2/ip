package Components;

import Functions.DateTimeHandler;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime dateTime;

    public Deadline(String description, String dateTime) {
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
        return "[D]" + super.toString() + " (by: " + getDateTime() + ")";
    }
}
