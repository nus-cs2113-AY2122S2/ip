package alexis.task;

public class Event extends Task {

    public Event(String description, String timing) {
        super(description, timing);
    }

    @Override
    public char typeOfTask() {
        return 'E';             //E represents Event
    }

    @Override
    public String getFullDescription() {
        return description + " (at: " + timing + ")";
    }
}
