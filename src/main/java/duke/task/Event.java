package duke.task;

public class Event extends Task {
    protected String timing;
    protected static final String EVENT_SYMBOL = "E";

    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "["+EVENT_SYMBOL+"]" + super.toString() + " (at: " + timing + ")";
    }
}
