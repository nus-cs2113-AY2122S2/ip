package util.tasks;

public class Events extends Task {
    /** Period of a task */
    protected String period;

    /**
     * Class constructor
     *
     * @param description the Task description.
     * @param period      the Task period.
     */
    public Events(String description, String period) {
        super(description);
        this.period = period;
    }

    /**
     * Returns the task's period.
     *
     * @return a period text.
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Updates the task's period.
     *
     * @param period the Task period.
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * Overrides the toString function with formatted details of the event.
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (at: " + getPeriod() + ")");
    }
}
