public class Event extends Task {
    
    protected String timing;

    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    @Override
    public String toString() {
        if (getStatus().equals("done")) {
            return "[E][X] " + getDescription() + " on: (" + this.timing + ")";
        } else {
            return "[E][ ] " + getDescription() + " on: (" + this.timing + ")";
        }
    }
}
