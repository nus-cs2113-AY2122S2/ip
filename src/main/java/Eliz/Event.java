package Eliz;

public class Event extends Task{
    private String taskType;

    public Event(String description) {
        super(description);
        taskType = "E";
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
