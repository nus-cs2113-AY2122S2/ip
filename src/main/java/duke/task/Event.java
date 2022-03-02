package duke.task;

import duke.task.Task;

public class Event extends Task {

    private String on;

    public Event(String name, String on) {
        super(name);
        this.on= on;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("(%s)", this.on) ;
    }
}
