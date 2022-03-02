package duke.task;

public class Event extends Task {
    protected String at;

    public Event (String description, String at) {
        super(description);
        this.at = at;
    }

    // Print event in a fixed format
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
