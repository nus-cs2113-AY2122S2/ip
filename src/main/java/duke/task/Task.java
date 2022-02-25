package duke.task;

public class Task {

    protected String description;
    protected boolean isDone;
    protected String info;

    public void setTaskType(char taskType) {
        this.taskType = taskType;
    }

    protected char taskType;

    public char getTaskType() {
        return taskType;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        if (isDone == true) {
            return "X";
        }
        else {
            return " ";
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String saveDescription() {
        return description;
    }

    public String saveAdditionalInfo() {
        return info;
    }
}
