package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return description;
    }

    public boolean getIsDone() { return (isDone ? true : false); }

    public String getDescription() { return description; }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with tick
    }

    public static String addIndentation() {
        return "    "; // adds an indentation of 4 spaces for aesthetic purposes
    }
}