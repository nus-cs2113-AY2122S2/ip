public class Deadlines extends Task {
    private final String DEAD_LINE_SYMBOL = "[D]";
    private String by;

    public Deadlines(String taskName, String by) {
        super(taskName);
        setBy(by);
    }

    public String printTaskDescription() {
        return DEAD_LINE_SYMBOL + super.printTaskDescription() + "(by: " + getBy() + ")";
    }

    public String setDone(boolean isDone) {
        String taskStatusSymbol;
        taskStatusSymbol = super.setDone(isDone);
        return DEAD_LINE_SYMBOL + taskStatusSymbol + this.getTaskName();
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

}
