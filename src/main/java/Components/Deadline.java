package Components;

public class Deadline extends Task {
    private String dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateTime() + ")";
    }
}
