package duke.task;

public class Events extends Task {
    private final String EVENTS_SYMBOL = "[E]";
    private String time;

    public Events(String taskName, String time) {
        super(taskName);
        setTime(time);
    }

    public String printTaskDescription() {
        return EVENTS_SYMBOL + super.printTaskDescription() + String.format("(at: %s)", getTime());
    }

    public String setDone(boolean isDone) {
        String taskStatusSymbol;
        taskStatusSymbol = super.setDone(isDone);
        return EVENTS_SYMBOL + taskStatusSymbol + this.getTaskName();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
