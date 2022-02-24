package duke.task;

public class Event extends Task{
    protected String at;

    public Event(String description, int taskUniqueID, String at) {
        super(description, taskUniqueID);
        this.at = at;
    }

    /**
     * Create the description message specific to Event objects.
     * @return String containing the message
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
