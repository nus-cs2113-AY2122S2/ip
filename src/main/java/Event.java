public class Event extends Task {
    private static final int EVENT_DESCRIPTION = 0;
    private static final int EVENT_PERIOD = 1;
    private static final String EVENT_CHECKBOX = "[E]";

    private String eventPeriod;

    public Event(String description, String eventPeriod) {
        super(description);
        this.eventPeriod = eventPeriod;
    }

    public Event(String[] descriptionAndPeriod) {
        super(descriptionAndPeriod[EVENT_DESCRIPTION]);
        this.eventPeriod = descriptionAndPeriod[EVENT_PERIOD];
    }

    public String getEventPeriod() {
        return eventPeriod;
    }

    public void setEventPeriod(String eventPeriod) {
        this.eventPeriod = eventPeriod;
    }

    public String toString() {
        String checkbox = getCheckbox();
        return String.format("%s%s %s (scheduled for: %s)", EVENT_CHECKBOX, checkbox, getDescription(), getEventPeriod());
    }
}
