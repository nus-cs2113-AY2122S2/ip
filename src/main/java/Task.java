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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean getCompleted() {
        return this.isCompleted;
    }

    public void setCompleted(boolean isDone) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            System.out.println("OK, I've marked this task as not done yet: ");
        }
        this.isCompleted = isDone;
    }

    public String getTaskStatus() {
        return (this.taskName + ": [" + getStatusIcon() + "]");
    }

}
