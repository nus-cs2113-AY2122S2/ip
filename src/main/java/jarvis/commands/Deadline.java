package jarvis.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime deadlineDate;
    private static final String DEADLINE_ICON = "D";
    private static final DateTimeFormatter STORING_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Deadline(String taskDescription, String deadlineDate, String deadlineTime) {
        super(taskDescription);
        this.deadlineDate = super.parseDate(deadlineDate, deadlineTime);
    }

    /**
     * Override getter function to retrieve Deadline's icon
     * @return An icon in String data type to represent a Deadline
     */
    public String getTypeIcon() {
        return DEADLINE_ICON;
    }

    /**
     * Override Getter function to retrieve Deadline's date
     * @return A String to specify the Deadline's date
     */
    public String getDeadlineDate() {
        if (deadlineDate != null) {
            return super.dateToString(this.deadlineDate);
        }
        return "";
    }

    /**
     * Override getter function to retrieve Deadline's description
     *
     * @return A String of Deadline's description
     */
    public String getDescription() {
        String message = super.getDescription() + " (by: " + getDeadlineDate() + ")";
        return message;
    }

    /**
     * Override function to export Deadline's data to be saved in a data file for when Jarvis is shutting down.
     *
     * @return String of formatted data of Deadline with icon, status, description and date.
     */
    public String exportData() {
        String status = isDone ? "YES" : "NO";
        return getTypeIcon() + " " + status + " " + super.getDescription() + " | " + this.exportDate();
    }

    private String exportDate() {
        return deadlineDate.format(STORING_FORMAT);
    }
}
