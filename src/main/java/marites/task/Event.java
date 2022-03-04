package marites.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private static final String EVENT_FORMAT_STRING = "[E]%s (at: %s)";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm 'at' yyyy-MM-dd");
    private LocalDateTime eventTime;

    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format(EVENT_FORMAT_STRING, super.toString(),
                DATE_TIME_FORMATTER.format(eventTime));
    }
}
