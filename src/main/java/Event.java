public class Event extends ToDo {
    protected String eventTime;

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
