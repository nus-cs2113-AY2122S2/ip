package duke.task;

public class Event extends Task{
    protected String at;

    public Event(String description, int taskUniqueID, String at) {
        super(description, taskUniqueID);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
