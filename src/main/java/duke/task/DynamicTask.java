package duke.task;

public abstract class DynamicTask extends Task {

    private String time;

    public DynamicTask(String description, String taskTypeSymbol, String time) {
        super(description, taskTypeSymbol);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
