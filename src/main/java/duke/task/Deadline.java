package main.java.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for the Deadline object. Deadlines are Tasks that do not have a specific due date.
 */

public class Deadline extends Task {

    private final String date;
    private final String time;
    private final LocalDate localDate;

    /**
     * Constructor for Deadline object.
     * 
     * @param description String describing the task.
     * @param date String date in YYYY-MM-DD that the task is due by .
     * @param time String time in 24-hour format that the task is due by.
     */
    public Deadline(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
        this.localDate = LocalDate.parse(date);
    }

    /**
     * Method used to show that the Task is a Deadline.
     * 
     * @return A String "D".
     */
    public String getType() {
        return "D";
    }

    /**
     * Method used to retrieve the date the deadline is due.
     * 
     * @return LocalDate of the due date.
     */
    public LocalDate getStartDate() {
        return this.localDate;
    }

    /**
     * Duplicate method used to retrieve the date the deadline is due.
     * 
     * @return LocalDate of the due date.
     */
    public LocalDate getEndDate() {
        return this.localDate;
    }

    /**
     * Formats the timing of the event into date|time for saving.
     * 
     * @return String in the format for writing to file.
     */
    public String getDateTime() {
        return this.date + "|" + this.time;
    }

    /**
     * Converts the parameters of the Deadline into a readable line with it's description
     * and due date and time.
     * 
     * @return String that contains the information of the Deadline.
     */
    @Override
    public String toString() {
        String dateTime = this.localDate.
                format(DateTimeFormatter.ofPattern("MMM d yyyy")).toString() + " " + this.time;
        return String.format("[D]%s (by: %s)", super.toString(), dateTime);
    }
}