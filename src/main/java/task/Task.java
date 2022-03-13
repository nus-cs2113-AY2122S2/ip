package task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A constructor of the task
     * @param description A string describes the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A constructor of the task
     * @param description A string describes the task
     * @param isDone A boolean value indicates whether the task has been done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * A method returns the description of the task
     * @return The description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * A method returns the status icon of the task which indicates if the task has been done
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return(isDone ? "X" : " ");
    }

    /**
     * A method returns the type icon of events
     * @return the icon of the events class
     */
    public String getType() {
        return "T";
    }

    /**
     * A method which marks the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * A method which marks the task as not done
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * A method which returns the string format of task
     * @return the string format of task
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s\n", this.getType(), this.getStatusIcon(), this.getDescription());
    }
}
