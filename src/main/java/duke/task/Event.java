package duke.task;



public class Event extends DatedTask {

    public Event(String name, String on) {
        super(name, on);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("(on: %s)", this.getDateTimeString()) ;
    }
}
