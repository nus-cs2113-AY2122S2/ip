public class Deadline extends Task {
    private String dueDate;
    private static final String DEADLINE_CHECKBOX = "[D]";

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String toString() {
        String checkbox = getCheckbox();
        return String.format("%s%s %s (due on: %s)", DEADLINE_CHECKBOX, checkbox, getDescription(), getDueDate());
    }
}
