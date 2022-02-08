package tasks;

public class Event extends Task {
    // Index values of the task details when reading in from the String array argument in the constructor
    private static final int EVENT_DESCRIPTION = 0;
    private static final int EVENT_PERIOD = 1;

    private static final String EVENT_CHECKBOX = "[E]";

    private String eventPeriod;

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
