public class Deadline extends Task {

    protected String by;

    public Deadline(String instruction, String by) {
        super(instruction);
        this.by = by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "  (D)" + super.toString() + this.instruction + "(by: " + by + ")";
    }
}