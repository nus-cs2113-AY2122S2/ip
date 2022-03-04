package task;

public class Deadline extends Task {
    protected String by;

    /**
     * Creates an instance of deadline object.
     * @param description description of the deadline.
     * @param by when is the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Prints out the deadline in a custom format.
     * @return String to be printed.
     */
    @Override
    public String toString() {
        return " [D][" + this.getStatusIcon() + "] " + this.description + " (by: " + this.by + ")";
    }

    //@@author quitejasper-reused
    //Reused from https://github.com/FaliciaOng/ip/blob/master/src/main/java/Deadline.java
    //with minor modifications

    /**
     * converts the deadline into a string that is to be written back into
     * the text document for storage.
     * @return String to be written back into storage.
     */
    @Override
    public String toFileString() {
        return "D " + super.toFileString() + " | " + by;
    }
    //@@author
}
