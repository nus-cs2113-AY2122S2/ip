package task;

public class Events extends Task {
    protected String duration;

    public Events(String description, String duration) {
        super(description);
        this.duration = duration;
        isDone = false;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)\n", this.getType(), this.getStatusIcon(), this.getDescription(), this.getDuration());
    }
}
