package Duke;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate deadlineTime;

    public Deadline(String content, String deadlineTime) throws DateTimeParseException {
        super(content);
        try {
            this.deadlineTime = LocalDate.parse(deadlineTime);
        } catch (DateTimeParseException e) {
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
