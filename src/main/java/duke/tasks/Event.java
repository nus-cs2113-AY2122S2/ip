package duke.tasks;

public class Event extends ToDo {
    protected String eventTime;

    /**
     * Constructor for Event object
     *
     * @param description Description of the event
     * @param eventTime the time of the event
     * @returns the deadline object
     */
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String getDescription() {
        String completeDescription = description + eventTime;
        return completeDescription;
    }

    @Override
    public String getStatusIcon() {
        String status = (isDone ? "X" : " ");
        String finalString = "[E][" + status + "]";
        return finalString;
    }
}
