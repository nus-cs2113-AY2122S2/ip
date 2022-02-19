package duke.task;

public class Task {
    protected static final String INDICATOR_MARKED = "[✓]";
    protected static final String INDICATOR_UNMARKED = "[ ]";

    protected String taskDescription;
    protected boolean isDone;

    public Task(String description) {
        this.taskDescription = description;
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String formatAsData(String FS) {
        return (isDone ? 1 : 0) + FS + this.taskDescription;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return INDICATOR_MARKED + " " + this.taskDescription;
        } else {
            return INDICATOR_UNMARKED + " " + this.taskDescription;
        }
    }
}
