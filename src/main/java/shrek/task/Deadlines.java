package shrek.task;

public class Deadlines extends UserContent {
    protected String by;

    public Deadlines(String description, String by) {
        super(description);
        setTaskName("D");
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}