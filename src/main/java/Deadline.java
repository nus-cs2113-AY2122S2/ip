public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        if (getStatus().equals("done")) {
            return "[D][X] " + getDescription() + " due: (" + this.dueDate + ")";
        } else {
            return "[D][ ] " + getDescription() + " due: (" + this.dueDate + ")";
        }
    }
}
