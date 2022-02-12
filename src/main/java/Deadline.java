public class Deadline extends Task {
    protected boolean isDone;
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
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
        return String.format("[D][%s] %s (by: %s)", status, super.toString().trim(), this.by.trim());
    }
}
