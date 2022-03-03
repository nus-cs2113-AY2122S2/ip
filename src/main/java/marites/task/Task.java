package marites.task;

public abstract class Task implements java.io.Serializable {
    private final String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isMatch(String query) {
        return description.contains(query);
    }

    @Override
    public String toString() {
        String doneIndicator = (isDone ? "[X]" : "[ ]");
        return String.format("%s %s", doneIndicator, this.description);
    }
}
