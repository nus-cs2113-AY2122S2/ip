public class Event extends Task {
    private static final String TASK_CODE = "E";
    protected String at;

    public Event(String taskDescription, String at) {
        super(taskDescription);
        this.at = at;
    }

    public Event(String taskDescription, boolean isCompleted, String at) {
        super(taskDescription, isCompleted);
        this.at = at;
    }

    @Override
    public String getTaskCode() {
        return TASK_CODE;
    }

    @Override
    public String getExtraInfo() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[" + getTaskCode() + "]" + super.toString() + " (at: " + at + ")\n";
    }
}
