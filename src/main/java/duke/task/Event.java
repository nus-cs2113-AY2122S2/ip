package duke.task;

public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String formatAsData(String FS) {
        return "E" + FS + super.formatAsData(FS) + FS + this.time;
    }

    @Override
    public String toString() {
        return "EVENT: " + super.toString() + " (at: " + this.time + ")";
    }
}
