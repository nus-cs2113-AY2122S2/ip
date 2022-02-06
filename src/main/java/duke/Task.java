public abstract class Task {
    private boolean isDone;
    public String taskKind = "[ ]";
    protected String description;
    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return "description: " + description;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }
}