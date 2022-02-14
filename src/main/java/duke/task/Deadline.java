package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    private DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.from(parseFormat.parse(by));
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDateTime.from(parseFormat.parse(by));
    }

    @Override
    public String getTask() {
        return "[D]" + super.getTask() + " (by: " + this.by.format(printFormat) + ")";
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + this.by.format(printFormat);
    }
}
