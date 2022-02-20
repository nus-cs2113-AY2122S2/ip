package shrek.task;

public class Events extends UserContent {
    protected String eventOccurAt;

    public Events(String description, String eventOccurAt) {
        super(description);
        setTaskName("E");
        this.eventOccurAt = eventOccurAt;
    }

    public String getEventOccurAt() {
        return eventOccurAt;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + eventOccurAt + ")";
    }
}
