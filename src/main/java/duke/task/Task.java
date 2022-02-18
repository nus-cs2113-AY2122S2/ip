package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task (boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    // Mark done task with X
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // Marks task as done. Returns the task string too
    public String markTask() {
        this.isDone = true;
        return this.toString();
    }

    // Unmark the task. Returns the task string too
    public String unmarkTask() {
        this.isDone = false;
        return this.toString();
    }

    // Output string when saved
    public String saveString() {
        int isDoneStr = isDone ? 1 : 0;
        return String.format(" | %d | %s", isDoneStr, this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
