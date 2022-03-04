package duke.tasklist;

public class Event extends Task {

    protected String timing;

    public Event(String description, String timing) {
        super(description);
        this.timing = timing; 
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + timing + ")" ; // mark done task with X
    }
}