public class Task {
    private boolean isDone;
    private String taskName;

    public Task(String taskName) {
        isDone = false;
        this.taskName = taskName;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
        if (this.isDone == true) {
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      [X] " + taskName);
        } else {
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      [ ] " + taskName);
        }
    }

    public boolean getDone() {
        return isDone;
    }

    public String getTaskName() {
        return taskName;
    }

}
