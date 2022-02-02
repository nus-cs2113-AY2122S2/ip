public class Deadline extends Task {
    private String deadlineTime;

    public Deadline(String content, String deadlineTime) {
        super(content);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadlineTime() + ")";
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }
}
