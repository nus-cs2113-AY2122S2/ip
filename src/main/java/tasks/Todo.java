package tasks;

public class Todo extends Task {
    private static final String TASK_CODE = "T";

    public Todo(String taskDescription) {
        super(taskDescription);
    }

    public Todo(String taskDescription, boolean isCompleted) {
        super(taskDescription, isCompleted);
    }

    @Override
    public String getTaskCode() {
        return TASK_CODE;
    }

    @Override
    public String getExtraInfo() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + getTaskCode() + "]" + super.toString();
    }
}
