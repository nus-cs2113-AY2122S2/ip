public class Event extends Task {

    public Event(String description, String timing) {
        super(description, timing);
    }

    @Override
    public char typeOfTask() {
        return 'E';
    }

    @Override
    public String fullDescription() {
        return description + " (at: " + timing + ")";
    }
}
