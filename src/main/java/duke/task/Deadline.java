package main.java.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final String date;
    private final String time;
    private final LocalDate localDate;

    public Deadline(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
        this.localDate = LocalDate.parse(date);
    }

    public String getType() {
        return "D";
    }

    public LocalDate getStartDate() {
        return this.localDate;
    }

    public LocalDate getEndDate() {
        return this.localDate;
    }

    public String getDateTime() {
        return this.date + "|" + this.time;
    }

    @Override
    public String toString() {
        String dateTime = this.localDate.
                format(DateTimeFormatter.ofPattern("MMM d yyyy")).toString() + " " + this.time;
        return String.format("[D]%s (by: %s)", super.toString(), dateTime);
    }
}