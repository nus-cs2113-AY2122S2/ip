package duke.task;

public class ToDo extends Task {
    private final String TO_DO_SYMBOL = "[T]";

    public ToDo(String taskName) {
        super(taskName);
    }

    public String printTaskDescription() {
        return TO_DO_SYMBOL + super.printTaskDescription();
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

}
