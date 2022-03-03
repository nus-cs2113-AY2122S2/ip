import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    String by;
    LocalDateTime byDate;

    /** format for printing datetime to string and reading from text file */
    private static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("LLL dd uuuu, hh:mm a");

    public Deadline(String task, String by, LocalDateTime byDate){
        super(task);
        this.by = by;
        this.byDate = byDate;
    }

    @Override
    public String toString(){
        if(byDate == null){
            return ("[D][" + getStatusIcon() + "] " + getDescription() + " (by: " + getBy() +")");
        }else{
            return ("[D][" + getStatusIcon() + "] " + getDescription() + " (by: " + getByDate().format(DATE_TIME_OUTPUT_FORMATTER) + ")");
        }
    }

    public String getBy() {
        return by;
    }

    public LocalDateTime getByDate(){
        return byDate;
    }

    public void setBy(String by) {
        this.by = by;
    }
}
