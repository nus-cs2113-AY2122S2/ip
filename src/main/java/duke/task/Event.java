package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Describes an upcoming event task which stores the description
 * and when the event is going to happen.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * The constructor which sets the given description of the event
     * and its time and assigns its task type accordingly.
     *
     * @param description The description of the event
     * @param at The time of the event
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
        this.taskType = 'E';
    }

    /**
     * Returns the string which represents all information of the task
     * to be printed to the standard output.
     *
     * @return A string consisting of all the information of the task to be printed
     */
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task
     */
    public String saveDescription() {
        return super.saveDescription();
    }

    /**
     * Gets the information of the task.
     *
     * @return The information of the task
     */
    public String saveAdditionalInfo() {
        return "/at" + at;
    }
}
