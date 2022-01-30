public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String markDone() {
        isDone = true;
        return String.format("Nice! I've marked this task as done:\n%s", toString());
    }

    public String unmarkDone() {
        isDone = false;
        return String.format("OK, I've marked this task as not done yet:\n%s", toString());
    }

    @Override
    public String toString() {
        String marking = "[ ]";
        if (isDone) {
            marking = "[X]";
        }
        return marking + " " + description;
    }
}
