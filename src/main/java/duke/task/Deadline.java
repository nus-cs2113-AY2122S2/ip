package duke.task;

public class Deadline extends Task{
    private static final String DEADLINE_LOGO = "[D]";
    private static final String BY_FIRST_HALF = " (by: ";
    private static final String BY_SECOND_HALF = ")";

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return DEADLINE_LOGO + super.toString() + BY_FIRST_HALF + by + BY_SECOND_HALF;
    }
}
