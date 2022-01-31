public class Event extends Task {

    private static final String EVENT_FORMAT_STRING = "[E]%s (at: %s)";
    private String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format(EVENT_FORMAT_STRING, super.toString(), eventTime);
    }
}
