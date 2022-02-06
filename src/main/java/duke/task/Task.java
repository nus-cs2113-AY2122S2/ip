package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isMarked;
    protected int taskNumber;

    public Task(String description, int taskNumber) {
        this.description = description;
        this.taskNumber = taskNumber;
        this.isMarked = false;
    }

    public Task() {
        this("This task has not be defined.", -1);
    }

    public String getStatusIcon() {
        return (isMarked ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public boolean getIsDone() {
        return isMarked;
    }

    public void setIsMarked() {
        isMarked = true;
    }

    public void unsetIsMarked() {
        isMarked = false;
    }

    @Override
    public String toString() {
        return  "[" + this.getStatusIcon() + "]" + this.getDescription();
    }
}