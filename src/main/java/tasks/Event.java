package tasks;

/**
 * Represents an event task
 * */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the event task in the form of a formatted string
     * The string is used to display event tasks to users
     *
     * @return String form of event task for display
     * */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns the event task in the form of a formatted string
     * The string is used to write event tasks to the user's task list file in the user's hard disk
     *
     * @return String form of event task for writing to task list file
     * */
    @Override
    public String getDetails() {
        return "E|" + (isDone ? "1" : "0") + "|" + description + "|" + at;
    }
}
