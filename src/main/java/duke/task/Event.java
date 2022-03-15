package duke.task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;

    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + super.toString() + " (at: " + at + ")";
    }

    public String getAt() { return at; }

    public void printAddToListMessage() {
        System.out.println("\nSuccessfully added to list:\n" + addIndentation() + "[E]" + getStatusIcon() + " "
                + description + " (at: " + at + ")");
    }
}