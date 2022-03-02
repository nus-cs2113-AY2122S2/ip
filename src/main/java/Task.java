/**
 * Represents the parent class for all task type.
 */
public class Task {
    protected String taskName;
    protected boolean isCompleted;

    public Task(String description) {
        this.taskName = description;
        this.isCompleted = false;
    }

    public String getStatusIcon() {
        if (this.isCompleted) {
            return "X";
        } else {
            return " ";
        }
    }

    public int getStatusNumber() {
        if (this.isCompleted) {
            return 1;
        } else {
            return 0;
        }
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean getCompleted() {
        return this.isCompleted;
    }

    public void setCompletedNoUpdateRequired(boolean isDone) {
        this.isCompleted = isDone;
    }

    public void setCompleted(boolean isDone) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            System.out.println("OK, I've marked this task as not done yet: ");
        }
        setCompletedNoUpdateRequired(isDone);
    }

    public String toFileString() {
        return "| " + getStatusNumber() + " | " + this.taskName;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.taskName;
    }
}
