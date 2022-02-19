package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate datetime;

    public Deadline(String description, LocalDate datetime) {
        super(description);
        this.datetime = datetime;
    }

    public Deadline(String description, Boolean isDone, LocalDate datetime) {
        super(description, isDone);
        this.datetime = datetime;
    }

    @Override
    public String getTaskType() {
        return "deadline";
    }

    @Override
    public String getDateFormattedString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM yyyy");
        String formattedDate =  datetime.format(dateFormat);
        return formattedDate;
    }

    @Override
    public String getDateForStorage() {
        return datetime.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateFormattedString() + ")";
    }
}
