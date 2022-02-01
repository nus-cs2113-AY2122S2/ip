public class Event extends Task{
    protected String eventTime;
    public Event(String title, String eventTime) {
        super(title);
        this.eventTime = eventTime;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public String toString()
    {
        return "[E]" + super.toString() + " (at: " + getEventTime() + ")";
    }
}
