public class Task {
    private String task;
    private boolean isDone;

    public Task(String task) {
        setTask(task);
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getTask() {
        return this.task;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void printTask() {
        String description = this.getTask();
        boolean isDone = this.getStatus();
        String status;

        if (isDone) {
            status = "[X] ";
        } else {
            status = "[ ] ";
        }

        String taskEntry = status + description;
        System.out.println(taskEntry);
    }
}
