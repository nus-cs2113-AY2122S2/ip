public class Event extends Todo{
    protected String at;

    public Event(String task, String at) throws NoDateException {
        super(task);
        this.at = at;

        if (this.at == null) {
            throw new NoDateException();
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
