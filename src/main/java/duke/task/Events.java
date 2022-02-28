package duke.task;

public class Events extends Task {
    private final String EVENTS_SYMBOL = "[E]";
    private String time;

    public Events(String taskName, String time) {
        super(taskName);
        setTime(time);
    }

    public String getTaskInformation() {
        return EVENTS_SYMBOL + super.getTaskInformation() + String.format(" (at: %s)", getTime());
    }
    public String getTaskDescription() {
        return super.getTaskDescription() + String.format(" (at: %s)", getTime());
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
