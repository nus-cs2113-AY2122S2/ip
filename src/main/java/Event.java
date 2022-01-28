public class Event extends Task {

    private final static String EVENT_MARKER = "[E]";
    protected String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return EVENT_MARKER + super.toString() + " (at: " + duration + ")";
    }
}