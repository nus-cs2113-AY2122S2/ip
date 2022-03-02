package jarvis.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime eventDate;
    private static final String EVENT_ICON = "E";
    private static final DateTimeFormatter STORING_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public String getTypeIcon() {
        return EVENT_ICON;
    }

    public Event(String eventDescription, String eventDay, String eventTime) {
        super(eventDescription);
        this.eventDate = super.parseDate(eventDay, eventTime);
    }

    public void printItem() {
        String message = "[" + getTypeIcon() + "]" + "[" + getStatusIcon() + "] " + getDescription();
        System.out.println(message);
    }

    public String getEventDate() {
        if (eventDate != null) {
            return super.dateToString(this.eventDate);
        }
        return "";
    }

    public String getDescription() {
        String message = super.getDescription() + " (at: " + getEventDate() + ")";
        return message;
    }

    public String exportData() {
        String status = isDone ? "YES" : "NO";
        return getTypeIcon() + " " + status + " " + super.getDescription() + " | " + this.exportDateTime();
    }

    private String exportDateTime() {
        return eventDate.format(STORING_FORMAT);
    }
}
