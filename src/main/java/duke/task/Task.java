package duke.task;

public class Task {
    protected String description;
    public boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void changeStatus() {
        this.isDone = !this.isDone;
    }

    public String getStatusIcon() {
        return ("[" + (isDone ? "X" : " ") + "] "); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}