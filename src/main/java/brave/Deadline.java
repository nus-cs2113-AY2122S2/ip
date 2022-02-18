package brave;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    public String getDescription() {
        return super.getDescription() + String.format(" (by: %s)", this.by);
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