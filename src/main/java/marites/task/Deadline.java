package marites.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private static final String DEADLINE_FORMAT_STRING = "[D]%s (by: %s)";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm 'at' yyyy-MM-dd");
    private LocalDateTime dueDate;

    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format(DEADLINE_FORMAT_STRING, super.toString(),
                DATE_TIME_FORMATTER.format(dueDate));
    }
}