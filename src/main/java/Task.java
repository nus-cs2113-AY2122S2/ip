public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        setDescription(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatus() {
        String mark = this.isDone ? "done" : "pending";
        return mark;
    }
}
