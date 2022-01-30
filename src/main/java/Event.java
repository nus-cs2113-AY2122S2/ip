public class Event extends Todo{
    protected String at;

    public Event(String task, String at) {
        super(task);
        this.at = at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
