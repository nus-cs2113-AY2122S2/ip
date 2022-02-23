package solana.task;

public class Event extends Task {
    protected String atDate;

    public Event(String description, String atDate) {
        super(description);
        this.atDate = atDate;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[E][X] " + description + " (At: " + atDate + ")";
        } else {
            return "[E][ ] " + description + " (At: " + atDate + ")";
        }
    }
}
