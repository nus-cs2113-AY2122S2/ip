package duke.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " " + super.toString() + " (by: " + by + ")";
    }

    public String getDeadline() { return by; }

    public void printAddToListMessage() {
        System.out.println("\nSuccessfully added to list:\n" + addIndentation() + "[D]" + getStatusIcon() + " "
                + description + " (by: " + by + ")");
    }
}