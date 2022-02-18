package task;

public class Events extends Task {
    protected String duration;

    /**
     * A constructor of events
     * @param description A string describe event
     * @param duration A string describe the time duration of the event
     */
    public Events(String description, String duration) {
        super(description);
        this.duration = duration;
        isDone = false;
    }

    /**
     * A constructor of events
     * @param description A string describe event
     * @param duration A string describe the time duration of the event
     * @param isDone A boolean value describe whether the events have been done
     */
    public Events(String description, String duration, boolean isDone) {
        super(description);
        this.duration = duration;
        this.isDone = isDone;
    }

    /**
     * A method returns duration of an event
     * @return the duration of the event
     */
    public String getDuration() {
        return duration;
    }

    /**
     * A method returns the type icon of events class
     * @return the icon of the events class
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * A method returns the string format of the event
     * @return the string format of the event
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)\n", this.getType(), this.getStatusIcon(), this.getDescription(), this.getDuration());
    }
}
