package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    private LocalDateTime by;


    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDateTime.parse(by);
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String by = this.by.format(formatter);
        return "[D]" + super.toString() + String.format("(by: %s)", by) ;
    }
}
