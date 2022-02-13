package vera.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getDate() {
        return at;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public void resetInput(String date) {
        at = date;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(at:" + at + ")";
    }
}
