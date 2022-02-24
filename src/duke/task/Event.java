package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate startDate;
    protected LocalTime startTime;
    protected LocalDate endDate;
    protected LocalTime endTime;
    public Event(String name, boolean marked, LocalDate startDate, LocalTime startTime,
                 LocalDate endDate, LocalTime endTime){
        super(name, marked);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public String getEventTime(){
        String eventTime = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ";
        eventTime += startTime.format(DateTimeFormatter.ofPattern("HH:mm")) + " to ";
        eventTime += endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ";
        eventTime += endTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        return eventTime;
    }

    public String getEventTimeDetails(){
        return startDate + " | " + startTime + " | " + endDate + " | " + endTime;
    }

    public String toString() {
        if (super.getMarked()) {
            return " [E][X] " + getName() + " (at: " + getEventTime() + ")";
        }else {
            return " [E][ ] " + getName() + " (at: " + getEventTime() + ")";
        }
    }

    public String getTaskDetails() {
        return "event | " + (getMarked() ? 1:0) + " | " + getName() + " | " + getEventTimeDetails() + "\n";
    }
}
