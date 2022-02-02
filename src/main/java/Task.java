public class Task {
    protected String description;
    protected boolean isDone;
    protected static final String BLANK_CHECKBOX = "[ ]";
    protected static final String DONE_CHECKBOX = "[X]";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    protected String getCheckbox() {
        return isDone() ? DONE_CHECKBOX : BLANK_CHECKBOX;
    }

    public String toString() {
        String checkbox = getCheckbox();

        return String.format("%s %s", checkbox, getDescription());
    }
}
