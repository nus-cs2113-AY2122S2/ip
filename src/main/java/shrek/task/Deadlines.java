package shrek.task;

public class Deadlines extends UserContent {
    protected String taskDueBy;

    public Deadlines(String description, String taskDueBy) {
        super(description);
        setTaskName("D");
        this.taskDueBy = taskDueBy;
    }

    public String getTaskDueBy() {
        return taskDueBy;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + taskDueBy + ")";
    }
}
