package Tasks;

/**
 * Represents a kind of task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public void setBy(String by){
        this.by=by;
    }

    public String getBy(){
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    @Override
    public String getType() {
        return "D";
    }
}
