public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public void printDescription() {
        if (isDone) {
            System.out.println("[E][X] " + description + " (At: " + at + ")");
        } else {
            System.out.println("[E][ ] " + description + " (At: " + at + ")");
        }
    }
}
