package brave.data;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getDescription() {
        return super.getDescription() + String.format(" (by: %s)", this.by);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString();
    }

    @Override
    public String getSaveFormat() {
        if (!isDone) {
            return "D , 0 , " + this.description + " , " + this.by;
        } else {
            return "D , 1 , " + this.description + " , " + this.by;
        }
    }
}