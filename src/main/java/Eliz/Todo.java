package Eliz;

/** Represents the specific task type Todo. Contains task descriptions and task type.*/
public class Todo extends Task{
    private String taskType;

    public Todo(String description) {
        super(description);
        taskType = "T";
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[" + taskType + "][" + getStatusIcon() + "] " + description;
    }
}
