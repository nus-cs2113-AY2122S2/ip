public abstract class Task {
    private String description;

    public Task(String description) {
        this.description = description;
    }

    abstract public String setDone(boolean isDone);

    @Override
    public String toString() {
        return description;
    }
}
