import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    String at;
    LocalDateTime atDate;

    /** format for printing datetime to string and reading from text file */
    private static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("LLL dd uuuu, hh:mm a");

    public Event(String task, String at, LocalDateTime atDate){
        super(task);
        this.at = at;
        this.atDate = atDate;
    }

    public String toString(){
        if(atDate == null){
            return ("[E][" + getStatusIcon() + "] " + getDescription() + " (at: " + getAt() +")");
        }else{
            return ("[E][" + getStatusIcon() + "] " + getDescription() + " (at: " + getAtDate().format(DATE_TIME_OUTPUT_FORMATTER) +")");
        }
    }

    public String getAt() {
        return at;
    }

    public LocalDateTime getAtDate(){
        return atDate;
    }

    public void setAt(String at) {
        this.at = at;
    }
}
