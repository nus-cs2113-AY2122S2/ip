package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isMarked;
    protected int taskUniqueID;

    public Task(String description, int taskUniqueID) {
        this.description = description;
        this.taskUniqueID = taskUniqueID;
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

    public int getTaskUniqueID() {
        return taskUniqueID;
    }

    public void setTaskUniqueID(int taskUniqueID) {
        this.taskUniqueID = taskUniqueID;
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

    /**
     * Create the portion of the description message common to the child classes.
     * @return String containing the message portion.
     */
    @Override
    public String toString() {
        return  "[" + this.getStatusIcon() + "]" + this.getDescription();
    }
}