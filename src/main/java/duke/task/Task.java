package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    private String symbol = "-";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatus() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }



}