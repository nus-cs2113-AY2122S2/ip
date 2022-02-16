package duke.tasks;

public class Event extends Task {

    // toString format string
    public static final String FORMAT_STRING = "[E][%c] %s (at: %s)";

    protected String atDate;

    /**
     * Create an Event with isDone set to false, description of choice and atDate of choice
     *
     * @param description Description of the Task to be created
     * @param atDate String representing event time
     */
    public Event(String description, String atDate) {
        this.description = description;
        this.isDone = false;
        this.atDate = atDate;
    }

    /**
     * Returns the atDate associated with event
     *
     * @return byDate
     */
    public String getAtDate() {
        return this.atDate;
    }

    @Override
    public String toString() {
        return String.format(FORMAT_STRING, this.getIsDone(), this.getDescription(), this.getAtDate());
    }
}
