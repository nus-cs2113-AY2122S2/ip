public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        System.out.println("\nSuccessfully added to list:\n" + addIndentation() + "[E]" + getTask() + " (at: " + at + ")");
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + super.toString() + " (at: " + at + ")";
    }
}