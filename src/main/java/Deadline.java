public class Deadline extends Task {

    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString(boolean isDone) {
        return "[D]" + super.toString(isDone) + " (by: " + deadline + ")";
    }
}