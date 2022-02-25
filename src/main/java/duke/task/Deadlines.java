package duke.task;

public class Deadlines extends Task {
    private final String DEAD_LINE_SYMBOL = "[D]";
    private String by;

    public Deadlines(String taskName, String by) {
        super(taskName);
        setBy(by);
    }

    public String printTaskDescription() {
        return DEAD_LINE_SYMBOL + super.printTaskDescription() + " (by: " + getBy() + ")";
    }

    public String addTaskMessage(){
        String message;
        message = super.addTaskMessage() + "\t   " + printTaskDescription();
        return message;
    }

    public String getTaskUpdatedMessage(){
        String message;
        message = super.getTaskUpdatedMessage() + "\t   " + printTaskDescription();
        return message;
    }

    public String removeTaskMessage(){
        String acknowledgementMessage = super.removeTaskMessage() + "\t   " + printTaskDescription();
        return acknowledgementMessage;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

}
