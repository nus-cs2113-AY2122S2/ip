package util.task;


public class Deadline extends Todo {
    protected String by;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    public String getTime() {
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
