package tasks;

/**
 * Event is a type of Task class
 */
public class Event extends Task {

    protected String at;

    /**
     * constructor
     *
     * @param description
     * @param at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at.trim();
    }

    /**
     * overwrite the print function
     *
     * @return
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
