public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public void resetInput(String date) {
        at = date;
        markAsUndone();
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(at:" + at + ")";
    }
}
