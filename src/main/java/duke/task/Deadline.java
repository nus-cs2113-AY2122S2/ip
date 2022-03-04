package duke.task;

public class Deadline extends DatedTask {


    public Deadline(String name, String by) {
        super(name, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by: %s)", this.getDateTimeString());
    }
}
