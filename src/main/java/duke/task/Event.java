package main.java.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for the Event object. An event is a task that has a start date and an end date.
 */

public class Event extends Task {
    
    private final String startDate;
    private final String startTime;
    private final String endDate;
    private final String endTime;
    private final LocalDate localStartDate;
    private final LocalDate localEndDate;

    /**
     * Constructor for the Event class.
     * 
     * @param description String describing the event.
     * @param startDate String of the date it starts on in YYYY-MM-DD.
     * @param startTime String of the time it starts in 24-hour format.
     * @param endDate String of the date it ends on in YYYY-MM-DD.
     * @param endTime String of the time it ends in 24-hour format.
     */
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

    /**
     * Method used to show that the Task is an Event.
     * 
     * @return A String "E"
     */
    public String getType() {
        return "E";
    }

    /**
     * Method used to retrieve the start date of the event.
     * 
     * @return LocalDate of the start date.
     */
    public LocalDate getStartDate() {
        return this.localStartDate;
    }

    /**
     * Method used to retrieve the end date of the event.
     * 
     * @return LocalDate of the end date.
     */
    public LocalDate getEndDate() {
        return this.localEndDate;
    }

    /**
     * Formats the timing of the event into start date|start time|end date|end time for saving.
     * 
     * @return String in the format for writing to file.
     */
    public String getDateTime() {
        return String.format("%s|%s|%s|%s", this.startDate, this.startTime, 
                this.endDate, this.endTime);
    }

    /**
     * Converts the parameters of the Event into a readable line with it's description, 
     * start and end dates and times.
     * 
     * @return String that contains the information of the Event.
     */
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