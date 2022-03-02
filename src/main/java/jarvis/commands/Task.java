package jarvis.commands;

import jarvis.display.DisplayMessages;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Task {
    protected String description;
    protected boolean isDone;
    private static final DateTimeFormatter STORE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
    private static final String TASK_ICON = "T";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public String getTypeIcon() {
        return TASK_ICON;
    }

    public boolean markAsDone() {
        boolean isTaskDone = this.isDone == true;
        if (isTaskDone) {
            DisplayMessages.unmarkError();
            DisplayMessages.horizontalLine();
            return false;
        }
        this.isDone = true;
        return true;
    }

    public boolean markAsUndone() {
        boolean isTaskUndone = this.isDone == false;
        if (isTaskUndone) {
            DisplayMessages.markError();
            return false;
        }
        this.isDone = false;
        return true;
    }

    public String getFullTask() {
        return "[" + getTypeIcon() + "]" + "[" + getStatusIcon() + "] " + getDescription();
    }

    public String exportData() {
        String status = isDone ? "YES" : "NO";
        String temp = getTypeIcon() + " " + status + " " + getDescription();
        return temp;
    }

    public static LocalDateTime parseDate(String date, String time) {
        LocalDateTime taskDate = null;
        try {
            taskDate = LocalDateTime.parse(date + " " + time, STORE_FORMAT);
        } catch (DateTimeParseException e) {
            DisplayMessages.invalidDateTime();
        }
        return taskDate;
    }

    public static String dateToString(LocalDateTime date) {
        return date.format(PRINT_FORMAT);
    }
}
