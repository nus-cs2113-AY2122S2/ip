public class Event extends Task {

    protected String event;

    public Event(String description, String event) {
        super(description);
        this.event = event;
    }

    @Override
    public String toString(boolean isDone) {
        return "[E]" + super.toString(isDone) + " (at: " + event + ")";
    }
}