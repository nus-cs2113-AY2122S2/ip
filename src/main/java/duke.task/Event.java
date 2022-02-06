package duke.task;

public class Event extends Task {
    private String eventTime;

    public Event(String taskInfo, String eventTime) {
        super(taskInfo);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        // ternary operator adapted from John McMonigle https://github.com/nus-cs2113-AY2122S2/ip/pull/56
        // rewrote return statement based on Clarence Chua Ying How's implementation https://github.com/nus-cs2113-AY2122S2/ip/pull/84
        String completion = (super.isDone() ? "X" : " ");
        return String.format("[E] [%s] %s (at: %s)", completion, super.getTaskInfo(), eventTime);
    }

}
