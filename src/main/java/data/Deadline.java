package data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with description and deadline date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Class constructor.
     *
     * @param description description of the deadline task
     * @param by description of the deadline task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Class constructor specifying whether the task is done.
     *
     * @param description description of the deadline task
     * @param by description of the deadline task
     * @param isDone whether the task is done
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ")";
    }

    @Override
    public String getInfo(){
        return "D / " + super.isDone() + " / " + super.getDescription() + " / " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
