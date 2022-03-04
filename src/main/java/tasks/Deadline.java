package tasks;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 * */
public class Deadline extends Task {

    protected LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Takes the deadline of a deadline task, which is of LocalDateTime class, and returns a formatted string
     *
     * @return Formatted string form of the deadline of a deadline task
     * */
    public String dateTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
    }

    /**
     * Returns the deadline task in the form of a formatted string
     * The string is used to display deadline tasks to users
     *
     * @return String form of deadline task for display
     * */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimeAsString() + ")";
    }

    /**
     * Returns the deadline task in the form of a formatted string
     * The string is used to write deadline tasks to the user's task list file in the user's hard disk
     *
     * @return String form of deadline task for writing to task list file
     * */
    @Override
    public String getDetails() {
        return "D|" + (isDone ? "1" : "0") + "|" + description + "|" + dateTimeAsString();
    }

}
