package shrek.task;

public class Events extends UserContent {
    protected String at;

    public Events(String description, String at) {
        super(description);
        setTaskName("E");
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
