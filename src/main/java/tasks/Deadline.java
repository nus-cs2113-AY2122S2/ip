package tasks;

import sora.SoraUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final String DEADLINE_CHECKBOX = "[D]";

    private LocalDateTime dueDate;

    public Deadline(String deadlineDescription, LocalDateTime dateAndTime) {
        super(deadlineDescription);
        this.dueDate = dateAndTime;
    }

    public String getDueDate() {
        DateTimeFormatter dateTimeFormat= DateTimeFormatter.ofPattern(SoraUI.DATE_TIME_OUTPUT_FORMAT);
        return dueDate.format(dateTimeFormat);
    }

    public String getDueDateForFileStorage() {
        DateTimeFormatter dateTimeFormat= DateTimeFormatter.ofPattern(SoraUI.DATE_TIME_INPUT_FORMAT);
        return dueDate.format(dateTimeFormat);
    }

    public String toString() {
        String checkbox = getCheckbox();
        return String.format("%s%s %s (due on: %s)", DEADLINE_CHECKBOX, checkbox, getDescription(), getDueDate());
    }
}
