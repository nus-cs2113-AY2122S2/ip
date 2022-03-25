package duke.tasks;

/**
 * Represents an Event Task
 */
public class Event extends Task {

    protected String at;

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String saveTasks() {
        return "E " + super.saveTasks() + " | " + this.at + System.lineSeparator();
    }
}
