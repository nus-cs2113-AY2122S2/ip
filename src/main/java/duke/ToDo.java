package duke;

public class ToDo extends Task {
    protected boolean isDone;

    public ToDo(String description) {
        super(description);
        isDone = false;
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
        return String.format("[T][%s] %s", status, super.toString());
    }
}
