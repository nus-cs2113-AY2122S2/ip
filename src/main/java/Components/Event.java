package Components;

public class Event extends Task {
    private String dateTime;

    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDateTime() + ")";
    }
}
