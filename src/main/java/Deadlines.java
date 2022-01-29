public class Deadline extends UserContent {
    private String taskName;
    protected String by;

    public Deadline(String description, String by, int listIndex) {
        super(description, listIndex);
        this.by = by;
    }
    @Override
    public void setTaskName() {
        this.taskName = "D";
    }
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}