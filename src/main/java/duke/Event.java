package duke;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return " [E][" + this.getStatusIcon() + "] " + this.description + " (at: " + this.at + ")";
    }

    //@@author quitejasper-reused
    //Reused from https://github.com/FaliciaOng/ip/blob/master/src/main/java/Event.java
    //with minor modifications
    @Override
    public String toFileString() {
        return "E " + super.toFileString() + " | " + at;
    }
    //@@author


}
