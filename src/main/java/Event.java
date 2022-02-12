public class Event extends Task {
    protected boolean isDone;
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.isDone = false;
    }

    @Override
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String status = null;
        if (isDone) {
            status = "X";
        } else {
            status = " ";
        }
        return String.format("[E][%s] %s (at: %s)", status, super.toString().trim(), this.at.trim());
    }
}
