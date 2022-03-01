package data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ")";
    }

    @Override
    public String getInfo(){
        return "E / " + super.isDone() + " / " + super.getDescription() + " / " + at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
