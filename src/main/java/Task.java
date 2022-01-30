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

    public String getStatusIcon() {
        return(isDone ? "X" : " ");
    }

    public String getType() {
        return "T";
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s\n", this.getType(), this.getStatusIcon(), this.getDescription());
    }
}
