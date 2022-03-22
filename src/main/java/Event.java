public class Event extends Task {
    private String eventPeriod;
    private static final String event_checkbox = "[E]";

    public Event(String description, String eventPeriod) {
        super(description);
        this.setEventPeriod(eventPeriod);
    }

    public String getEventPeriod() {
        return eventPeriod;
    }

    public void setEventPeriod(String eventPeriod) {
        this.eventPeriod = eventPeriod;
    }

    public String toString() {
        String checkbox = getCheckbox();
        return String.format("%s%s %s (scheduled for: %s)", event_checkbox, checkbox, getDescription(), getEventPeriod());
    }
}