public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void printDescription() {
        if (isDone) {
            System.out.println("[D][X] " + description + " (By: " + by + ")");
        } else {
            System.out.println("[D][ ] " + description + " (By: " + by + ")");
        }
    }
}
