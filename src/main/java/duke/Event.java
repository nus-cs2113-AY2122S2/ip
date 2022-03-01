package duke;

/**
 * This class represent a task that need to be done at the specific time.
 */
public class Event extends Task {
    protected boolean isDone;
    protected String at;

    /**
     * Store the description and the time of the given Event object.
     * @param description the description of the Event.
     * @param at the specific time of the Event that will occur
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.isDone = false;
    }

    /**
     * mark the Event object as completed.
     */
    @Override
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * mark the Event object as incomplete.
     */
    @Override
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * A String representation of the given Event object.
     * @return the String representation of the given Event object.
     */
    @Override
    public String toString() {
        String status = null;
        if (isDone) {
            status = "X";
        } else {
            status = " ";
        }
        return String.format("[E][%s] %s (at: %s)", status, super.toString().trim(), this.at.trim());
    }
}
