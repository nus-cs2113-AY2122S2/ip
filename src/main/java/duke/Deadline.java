package duke;

public class Deadline extends Task{
    protected String by;
    public Deadline(String title, String by) {
        super(title);
        this.by = by;
    }
    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
    @Override
    public String toString()
    {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}
