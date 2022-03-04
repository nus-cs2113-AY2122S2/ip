package task;

public class Event extends Task {
    protected String at;

    /**
     * Creates Event obect
     * @param description
     * @param at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Prints out the event in a custom format.
     * @return String to be printed.
     */
    @Override
    public String toString() {
        return " [E][" + this.getStatusIcon() + "] " + this.description + " (at: " + this.at + ")";
    }

    //@@author quitejasper-reused
    //Reused from https://github.com/FaliciaOng/ip/blob/master/src/main/java/Event.java
    //with minor modifications

    /**
     * converts the event into a string that is to be written back into
     * the text document for storage.
     * @return String to be written back into storage.
     */
    @Override
    public String toFileString() {
        return "E " + super.toFileString() + " | " + at;
    }
    //@@author


}
