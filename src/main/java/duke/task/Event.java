package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private String location;
    private LocalDate dateOfEvent;

    public Event(String description, String location, LocalDate dateOfEvent, String typeOfTask) {
        super(description, typeOfTask);
        this.location = location;
        this.dateOfEvent = dateOfEvent;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getDate() {
        return dateOfEvent;
    }

    @Override
    public String toString() {
        String date = dateOfEvent.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + location + " on: " + date + ")";
    }

}
