public class Deadline extends Task {
    protected String deadlineBy;

    public Deadline(String description, String deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadlineBy + ")";
    }
}
