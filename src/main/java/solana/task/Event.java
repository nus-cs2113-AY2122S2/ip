package solana.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[E][X] " + description + " (At: " + at + ")";
        } else {
            return "[E][ ] " + description + " (At: " + at + ")";
        }
    }
}
