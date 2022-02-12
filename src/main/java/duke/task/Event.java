package duke.task;

import duke.task.Task;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, Boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getTaskType() {
        return "event";
    }

    @Override
    public String getDate() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
