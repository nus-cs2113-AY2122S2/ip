package tasks;

/**
 * Represents a Task of type Event. Event tasks contain a description of the task as well as a date and time
 * that represents the date and time that the event will occur.
 */
public class Event extends Task {
    // Index values of the task details when reading in from the String array argument in the constructor
    private static final int EVENT_DESCRIPTION = 0;
    private static final int EVENT_PERIOD = 1;
    private static final String EVENT_CHECKBOX = "[E]";

    private String eventPeriod;

    /**
     * Constructs an Event task with the specified description and event period.
     *
     * @param descriptionAndPeriod A string array containing the event description and period.
     */
    public Event(String[] descriptionAndPeriod) {
        super(descriptionAndPeriod[EVENT_DESCRIPTION]);
        this.eventPeriod = descriptionAndPeriod[EVENT_PERIOD];
    }

    /**
     * Gets the event period of the Event instance.
     *
     * @return The event period.
     */
    public String getEventPeriod() {
        return this.eventPeriod;
    }

    /**
     * Sets the event period of the Event instance to a specified period.
     *
     * @param eventPeriod The new period for the event.
     */
    public void setEventPeriod(String eventPeriod) {
        this.eventPeriod = eventPeriod;
    }

    /**
     * Returns a string representation of the Event instance.
     *
     * @return A string representation of the Event instance.
     */
    public String toString() {
        String checkbox = getCheckbox();
        return String.format("%s%s %s (scheduled for: %s)", EVENT_CHECKBOX, checkbox, getDescription(), getEventPeriod());
    }
}
