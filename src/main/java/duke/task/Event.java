package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task{

    private String location;
    private LocalDate dateOfEvent;

    public Event(String description, String location, LocalDate dateOfEvent, String typeOfTask) {
        super(description, typeOfTask);
        this.location = location;
        this.dateOfEvent = dateOfEvent;
    }

    /**
     * Returns the location of this event task.
     *
     * @return The location of this event task.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the date of this event task.
     *
     * @return The date of event of this event task.
     */
    public LocalDate getDate() {
        return dateOfEvent;
    }

    /**
     * Returns a particular format to represent this event task.
     *
     * @return The printing format of this event task.
     */
    @Override
    public String toString() {
        String date = dateOfEvent.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + location + " on: " + date + ")";
    }

}
