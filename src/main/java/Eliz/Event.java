package Eliz;

import java.io.IOException;

/** Represents the specific task type Event. Contains task descriptions and task type.*/
public class Event extends Task{
    private String taskType;

    public Event(String description) {
        super(description);
        taskType = "E";
    }

    /** Get the task type of the task. */
    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[" + taskType + "][" + getStatusIcon() + "] " + description;
    }

}
