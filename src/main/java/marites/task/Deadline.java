package marites.task;

public class Deadline extends Task {

    private static final String DEADLINE_FORMAT_STRING = "[D]%s (by: %s)";

    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format(DEADLINE_FORMAT_STRING, super.toString(), dueDate);
    }
}
