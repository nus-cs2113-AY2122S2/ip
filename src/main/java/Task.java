public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }
    public char getStatusIcon() {
        return (isDone ? 'X' : ' '); // mark done task with X
    }

    public void setStatusIcon(boolean done) {
        this.isDone = done;
    }
}