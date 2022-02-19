package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate datetime;

    public Event(String description, LocalDate datetime) {
        super(description);
        this.datetime = datetime;
    }

    public Event(String description, Boolean isDone, LocalDate datetime) {
        super(description, isDone);
        this.datetime = datetime;
    }

    @Override
    public String getTaskType() {
        return "event";
    }

    @Override
    public String getDateFormattedString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM yyyy");
        String formattedDatetime =  datetime.format(dateFormat);
        return formattedDatetime;
    }

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
        return "[E]" + super.toString() + " (at: " + getDateFormattedString() + ")";
    }
}
