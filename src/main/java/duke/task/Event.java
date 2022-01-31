package duke.task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getTask() {
        return "[E]" + super.getTask() + " (at: " + at + ")";
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + this.at;
    }
}
