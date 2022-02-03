public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.type = "D";
        this.by = by;
    }

    public String getBy() {

        return this.by;
    }

    public void setBy(String by) {

        this.by = by;
    }

    @Override
    public String toString() {

        return super.toString() + " (by: " + by + ")";
    }
}
