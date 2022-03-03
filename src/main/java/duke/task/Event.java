package main.java.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    
    private final String startDate;
    private final String startTime;
    private final String endDate;
    private final String endTime;
    private final LocalDate localStartDate;
    private final LocalDate localEndDate;

    public Event(String description, String startDate, String startTime, 
            String endDate, String endTime) {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.localStartDate = LocalDate.parse(startDate);
        this.localEndDate = LocalDate.parse(endDate);
    }

    public String getType() {
        return "E";
    }

    public LocalDate getStartDate() {
        return this.localStartDate;
    }

    public LocalDate getEndDate() {
        return this.localEndDate;
    }

    public String getDateTime() {
        return String.format("%s|%s|%s|%s", this.startDate, this.startTime, 
                this.endDate, this.endTime);
    }

    @Override
    public String toString() {
        String startDateTime = this.localStartDate.
                format(DateTimeFormatter.ofPattern("MMM d yyyy")).toString() + " " + 
                this.startTime;
        String endDateTime = this.localEndDate.
                format(DateTimeFormatter.ofPattern("MMM d yyyy")).toString() + " " + 
                this.endTime;
        return String.format("[E]%s (at: %s to %s)", super.toString(), 
                startDateTime, endDateTime);
    }
}