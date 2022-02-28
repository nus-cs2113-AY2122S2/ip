package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadlineTime;

    public Deadline(String content, String deadlineTime) {
        super(content);
        try {
            this.deadlineTime = LocalDate.parse(deadlineTime);
        } catch (Exception e) {
            this.deadlineTime = LocalDate.parse(deadlineTime, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadlineTime() + ")";
    }

    public String getDeadlineTime() {
        return deadlineTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
