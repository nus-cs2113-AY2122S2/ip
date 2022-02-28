package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate schedule;

    public Event(String content, String schedule) {
        super(content);
        try {
            this.schedule = LocalDate.parse(schedule);
        } catch (DateTimeParseException e) {
            try {
                this.schedule = LocalDate.parse(schedule, DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (DateTimeParseException e1) {
                this.schedule = LocalDate.parse("1970-01-01");
            }
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getSchedule() + ")";
    }

    public String getSchedule() {
        return schedule.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
