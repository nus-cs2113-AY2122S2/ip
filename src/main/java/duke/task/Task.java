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

    /** Mark done task with X */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /** Marks task as done. Returns the task string too */
    public Task markTask() {
        this.isDone = true;
        return this;
    }

    /** Unmark the task. Returns the task string too */
    public Task unmarkTask() {
        this.isDone = false;
        return this;
    }

    public boolean contains(String keyword) {
        return this.description.contains(keyword);
    }

    /** Output string when saved in storage */
    public String saveString() {
        int isDoneStr = isDone ? 1 : 0;
        return String.format(" | %d | %s", isDoneStr, this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
