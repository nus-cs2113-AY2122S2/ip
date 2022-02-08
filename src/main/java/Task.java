public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    private String getDoneIcon() {
        String icon = "[ ] ";
        if (isDone) {
            icon = "[X] ";
        }
        return icon;
    }

    @Override
    public String toString() {
        return getDoneIcon() + description;
    }
}
