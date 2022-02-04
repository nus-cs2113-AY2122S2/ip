public class Event extends Task {
    private String eventTime;

    public Event(String taskInfo, String eventTime) {
        super(taskInfo);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        String completion = " ";
        if (super.isDone()) {
            completion = "X";
        }
        return "[E][" + completion + "] " + super.getTaskInfo() + "(at: " + eventTime + ")";
    }

}
