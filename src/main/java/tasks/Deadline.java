package tasks;

public class Deadline extends Task {
    private static final String TASK_CODE = "D";
    protected String by;

    public Deadline(String taskDescription, String by) {
        super(taskDescription);
        this.by = by;
    }

    public Deadline(String taskDescription, boolean isCompleted, String by) {
        super(taskDescription, isCompleted);
        this.by = by;
    }

    @Override
    public String getTaskCode() {
        return TASK_CODE;
    }

    @Override
    public String getExtraInfo() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[" + getTaskCode() + "]" + super.toString() + " (by: " + by + ")l";
    }
}
