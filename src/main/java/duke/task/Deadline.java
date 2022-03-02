package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends DatedTask {


    public Deadline(String name, String by) {
        super(name, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by: %s)", this.getDateTimeString()) ;
    }
}
