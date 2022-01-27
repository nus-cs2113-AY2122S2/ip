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

    public void setIsDone(boolean status) {
        isDone = status;
    }

    public String getStatusIcon() {
        String s = (isDone ? "X" : " ");
        return s;
    }
}
