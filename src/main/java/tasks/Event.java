package tasks;

import sora.SoraUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task of type Event. Event tasks contain a description of the task as well as a date and time
 * that represents the date and time that the event will occur.
 */
public class Event extends Task {
    private static final String EVENT_CHECKBOX = "[E]";

    private LocalDateTime eventPeriod;

    /**
     * Constructs an Event task with the specified description and event period.
     *
     * @param eventDescription The description of the Event task.
     * @param dateAndTime The date and time that the Event task is expected to take place.
     */
    public Event(String eventDescription, LocalDateTime dateAndTime) {
        super(eventDescription);
        this.eventPeriod = dateAndTime;
    }

    /**
     * Returns a string representation of the event period in a format that is meant for displaying
     * to the user.
     *
     * @return The event period of the instance in a format for displaying to the user.
     */
    public String getEventPeriod() {
        DateTimeFormatter dateTimeFormat= DateTimeFormatter.ofPattern(SoraUI.DATE_TIME_OUTPUT_FORMAT);
        return eventPeriod.format(dateTimeFormat);
    }

    /**
     * Returns a string representation of the event period in a format that is meant for exporting
     * to the file storage.
     *
     * @return The event period of the instance in a format for exporting to the file storage.
     */
    public String getEventPeriodForFileStorage() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(SoraUI.DATE_TIME_INPUT_FORMAT);
        return eventPeriod.format(dateTimeFormat);
    }

    /**
     * Returns a string representation of the Event instance.
     *
     * @return A string representation of the Event instance.
     */
    public String toString() {
        String checkbox = getCheckbox();
        return String.format("%s%s %s (scheduled for: %s)", EVENT_CHECKBOX, checkbox, getDescription(), getEventPeriod());
    }
}
