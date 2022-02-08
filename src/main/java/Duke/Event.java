package Duke;

public class Event extends Task {
    private final String schedule;

    public Event(String content, String schedule) {
        super(content);
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getSchedule() + ")";
    }

    public String getSchedule() {
        return schedule;
    }
}
