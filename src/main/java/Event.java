public class Event extends Task {
    private String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    public String getDescription() {
        return super.getDescription() + String.format(" (at: %s)", this.eventTime);
    }
}