public class Deadline extends Task {
    private static final int DEADLINE_DESCRIPTION = 0;
    private static final int DEADLINE_DATE = 1;
    private static final String DEADLINE_CHECKBOX = "[D]";

    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public Deadline(String[] descriptionAndDate) {
        super(descriptionAndDate[DEADLINE_DESCRIPTION]);
        this.dueDate = descriptionAndDate[DEADLINE_DATE];
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
