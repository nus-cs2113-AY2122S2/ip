public class Deadline extends Todo {
    protected String by;

    public Deadline(String task, String by) throws NoDateException {
        super(task);
        this.by = by;

        if (this.by == null) {
            throw new NoDateException();
        }
    }

    public String getTime() {
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
