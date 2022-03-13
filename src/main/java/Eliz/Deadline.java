package Eliz;

/** Represents the specific task type Deadline. Contains task descriptions and task type.*/
public class Deadline extends Task{
    private String taskType;

    public Deadline(String description) {
        super(description);
        taskType = "D";
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
