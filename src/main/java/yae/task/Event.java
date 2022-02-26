package yae.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTaskType() {
        return "event";
    }

    @Override
    public String getTime() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ')';
    }
}
