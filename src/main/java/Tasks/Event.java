package Tasks;

/**
 * Represents a kind of task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public void setAt(String at){
        this.at=at;
    }

    public String getAt(){
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

    @Override
    public String getType() {
        return "E";
    }
}

