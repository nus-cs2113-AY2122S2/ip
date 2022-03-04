package tasks;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public String dateTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimeAsString() + ")";
    }

    @Override
    public String getDetails() {
        return "D|" + (isDone ? "1" : "0") + "|" + description + "|" + dateTimeAsString();
    }

}
