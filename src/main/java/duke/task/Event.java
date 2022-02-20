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
     * This is the getLocation method that returns the location of this Event task.
     *
     * @return The location of this Event task.
     */
    public String getLocation() {
        return location;
    }

    /**
     * This is the getDate method that returns the location of this Event task.
     *
     * @return The date of this Event task.
     */
    public LocalDate getDate() {
        return dateOfEvent;
    }

    /**
     * This is the toString method that returns a particular format to represent this Event task.
     *
     * @return The printing format of this Event task.
     */
    @Override
    public String toString() {
        String date = dateOfEvent.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + location + " on: " + date + ")";
    }

}
