package data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent an event task with description and the happening date and time of it.
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Class constructor.
     *
     * @param description description of the event task
     * @param at happening date and time of the event task
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Class constructor specifying whether the task is done.
     *
     * @param description description of the event task
     * @param at happening date and time of the event task
     * @param isDone whether the task is done
     */
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
