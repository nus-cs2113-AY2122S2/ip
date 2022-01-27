public class Task {
    private String task;
    private boolean isDone;

    public Task(String item) {
        this.task = item;
        this.isDone = false;
    }

    public String getTask() {
        return this.task;
    }


    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
}
