package duke.task;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DatedTask extends Task {

    private String DateTimeString;
    private LocalDateTime RealDateTime;


    public DatedTask(String name, String DateTimeString) {
        super(name);
        this.DateTimeString = DateTimeString;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            this.RealDateTime = LocalDateTime.parse(DateTimeString, formatter);
        }
        catch (Exception e) {
        }

    }

    /**
     * Get date and time in string
     * @return date and time
     */
    public String getDateTimeString(){
        String DateTimeString = this.DateTimeString;

        if (this.RealDateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            DateTimeString = this.RealDateTime.format(formatter);
        }
        return DateTimeString;
    }

}
