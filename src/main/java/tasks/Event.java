package tasks;

import sora.SoraUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final String EVENT_CHECKBOX = "[E]";

    private LocalDateTime eventPeriod;

    public Event(String eventDescription, LocalDateTime dateAndTime) {
        super(eventDescription);
        this.eventPeriod = dateAndTime;
    }

    public String getEventPeriod() {
        DateTimeFormatter dateTimeFormat= DateTimeFormatter.ofPattern(SoraUI.DATE_TIME_OUTPUT_FORMAT);
        return eventPeriod.format(dateTimeFormat);
    }

    public String getEventPeriodForFileStorage() {
        DateTimeFormatter dateTimeFormat= DateTimeFormatter.ofPattern(SoraUI.DATE_TIME_INPUT_FORMAT);
        return eventPeriod.format(dateTimeFormat);
    }

    public String toString() {
        String checkbox = getCheckbox();
        return String.format("%s%s %s (scheduled for: %s)", EVENT_CHECKBOX, checkbox, getDescription(), getEventPeriod());
    }
}
