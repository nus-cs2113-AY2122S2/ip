package bim.task;

public class Event extends Task {
    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
