package duke.task;

public class ToDo extends Task {
    public ToDo(String taskInfo) {
        super(taskInfo);
    }

    @Override
    public String toString() {
        // rewrote return statement based on Clarence Chua Ying How's implementation https://github.com/nus-cs2113-AY2122S2/ip/pull/84
        return String.format("[T] %s", super.toString());
    }
}
