abstract class Task {
    protected String description;

    public Task(String item) {
        this.description = item;
    }

    public String getDescription() {
        return this.description;
    }

    abstract public void markAsDone();

    abstract public void markAsNotDone();

    @Override
    public String toString() {
        return this.description;
    }
}
