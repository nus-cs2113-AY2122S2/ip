package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event object. A Task with a timing
 */
public class Event extends Task {
    protected LocalDate startDate;
    protected LocalTime startTime;
    protected LocalDate endDate;
    protected LocalTime endTime;

    public Event(String name, boolean marked, LocalDate startDate, LocalTime startTime,
                 LocalDate endDate, LocalTime endTime) {
        super(name, marked);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    /**
     * Get the event timing in a string format that is more readible for the user
     * @return a string of the event timing that is more readible for the user
     */
    private String getEventTime() {
        String eventTime = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ";
        eventTime += startTime.format(DateTimeFormatter.ofPattern("HH:mm")) + " to ";
        eventTime += endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ";
        eventTime += endTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        return eventTime;
    }

    /**
     * Get a string representation of the event time in the save format
     * @return string of the event time in the save format
     */
    public String getEventTimeDetails(){
        return startDate + " | " + startTime + " | " + endDate + " | " + endTime;
    }

    /**
     * Converts the Event object into string format
     * @return a string representation of the Event object
     */
    public String toString() {
        if (super.getMarked()) {
            return " [E][X] " + getName() + " (at: " + getEventTime() + ")";
        }else {
            return " [E][ ] " + getName() + " (at: " + getEventTime() + ")";
        }
    }

    /**
     * Converts the Event object into string format for storage
     * @return a string representation of the Event object to be stored
     */
    public String getTaskDetails() {
        return "event | " + (getMarked() ? 1:0) + " | " + getName() + " | " + getEventTimeDetails() + "\n";
    }
}
