package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DatedTask extends Task {

    private String DateTimeString;
    private LocalDateTime RealDateTime;

    /**
     * Task that has date/time attribute
     * @param taskName name of task
     * @param DateTimeString optional date/time in string format "dd-MM-yyyy HH:mm, "" if none
     */
    public DatedTask(String taskName, String DateTimeString) {
        super(taskName);
        this.DateTimeString = DateTimeString;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            this.RealDateTime = LocalDateTime.parse(DateTimeString, formatter);
        } catch (Exception e) {
            this.RealDateTime = null;
        }

    }

    /**
     * Get date and time in string
     * @return date and time
     */
    public String getDateTimeString() {
        String dateTimeString = this.DateTimeString;

        if (this.RealDateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            dateTimeString = this.RealDateTime.format(formatter);
        }
        return dateTimeString;
    }

}
