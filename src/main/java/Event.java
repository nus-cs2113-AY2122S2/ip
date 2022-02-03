public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        super(description);
        this.type = "E";
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    public void setAt(String at) {

        this.at = at;
    }

    @Override
    public String toString() {

        return super.toString() + " (at: " + at + ")";
    }
}
