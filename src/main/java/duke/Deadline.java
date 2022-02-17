package duke;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return " [D][" + this.getStatusIcon() + "] " + this.description + " (by: " + this.by + ")";
    }

    //@@author quitejasper-reused
    //Reused from https://github.com/FaliciaOng/ip/blob/master/src/main/java/Deadline.java
    //with minor modifications
    @Override
    public String toFileString() {
        return "D " + super.toFileString() + " | " + by;
    }
    //@@author
}
