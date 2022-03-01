package duke.task;

public class Deadlines extends Task {
    private final String DEAD_LINE_SYMBOL = "[D]";
    private String by;

    public Deadlines(String taskName, String by) {
        super(taskName);
        setBy(by);
    }

    public String getTaskInformation() {
        return DEAD_LINE_SYMBOL + super.getTaskInformation() + " (by: " + getBy() + ")";
    }

    public String getTaskDescription(){
        return super.getTaskDescription() + " (by: " + getBy() + ")";
    }

    public String addTaskMessage(){
        String message;
        message = super.addTaskMessage() + "\t   " + getTaskInformation();
        return message;
    }

    public String getTaskUpdatedMessage(){
        String message;
        message = super.getTaskUpdatedMessage() + "\t   " + getTaskInformation();
        return message;
    }

    public String removeTaskMessage(){
        String acknowledgementMessage = super.removeTaskMessage() + "\t   " + getTaskInformation();
        return acknowledgementMessage;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

}
