package jarvis.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime deadlineDate;
    private static final String DEADLINE_ICON = "D";
    private static final DateTimeFormatter STORING_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public String getTypeIcon() {
        return DEADLINE_ICON;
    }

    public Deadline(String taskDescription, String deadlineDate, String deadlineTime) {
        super(taskDescription);
        this.deadlineDate = super.parseDate(deadlineDate, deadlineTime);
    }

    public void printItem() {
        String message = "[" + getTypeIcon() + "]" + "[" + getStatusIcon() + "] " + getDescription();
        System.out.println(message);
    }

    public String getDeadlineDate() {
        if (deadlineDate != null) {
            return super.dateToString(this.deadlineDate);
        }
        return "";
    }

    public String getDescription() {
        String message = super.getDescription() + " (by: " + getDeadlineDate() + ")";
        return message;
    }

    public String exportData() {
        String status = isDone ? "YES" : "NO";
        return getTypeIcon() + " " + status + " " + super.getDescription() + " | " + this.exportDate();
    }

    private String exportDate() {
        return deadlineDate.format(STORING_FORMAT);
    }
}
