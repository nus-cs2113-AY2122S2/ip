package duke.task;

public class ToDo extends Task {
    private final String TO_DO_SYMBOL = "[T]";

    public ToDo(String taskName) {
        super(taskName);
    }

    public String getTaskInformation() {
        return TO_DO_SYMBOL + super.getTaskInformation();
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

}
