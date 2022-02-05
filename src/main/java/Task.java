public class Task {
    public static final String INDICATOR_MARKED = "[✓]";
    public static final String INDICATOR_UNMARKED = "[ ]";

    protected String taskDescription;
    protected boolean isDone;

    public Task(String description) {
        this.taskDescription = description;
        this.isDone = false;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
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
