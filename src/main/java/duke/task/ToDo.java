package duke.task;

public class ToDo extends Task {
    private final String TO_DO_SYMBOL = "[T]";

    public ToDo(String taskName) {
        super(taskName);
    }

    public String printTaskDescription() {
        return TO_DO_SYMBOL + super.printTaskDescription();
    }

    public String setDone(boolean isDone) {
        String taskStatusSymbol;
        taskStatusSymbol = super.setDone(isDone);
        return TO_DO_SYMBOL + taskStatusSymbol + this.getTaskName();
    }

}
