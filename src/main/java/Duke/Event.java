package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate schedule;

    public Event(String content, String schedule) {
        super(content);
        try {
            this.schedule = LocalDate.parse(schedule);
        } catch (Exception e) {
            this.schedule = LocalDate.parse(schedule, DateTimeFormatter.ofPattern("MMM dd yyyy"));
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
