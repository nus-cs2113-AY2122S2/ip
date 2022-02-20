package duke.task;

/**
 * Represents an Event task.
 */
public class Event extends Task{
    public static final String EVENT_LOGO = "[E]";
    public static final String AT_FIRST_HALF = " (at: ";
    public static final String AT_SECOND_HALF = ")";
    protected String at;

    public Event(String description, String at, String typeOfTask) {
        super(description, typeOfTask);
        this.at = at;
    }

    /**
     * This is the toString method that returns a particular format to represent this Event task.
     *
     * @return The printing format of this Event task.
     */
    @Override
    public String toString() {
        return EVENT_LOGO + super.toString() + AT_FIRST_HALF + at + AT_SECOND_HALF;
    }

    /**
     * This is the getAt method that returns the location of this Event task.
     *
     * @return The location of this Event task.
     */
    public String getAt() {
        return at;
    }
}
