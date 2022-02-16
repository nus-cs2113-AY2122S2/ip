package duke.task;

public class Task {
    private boolean isDone;
    private String taskName;
    private final String DONE_SYMBOL = "[X] ";
    private final String NOT_DONE_SYMBOL = "[ ] ";

    public Task(String taskName) {
        isDone = false;
        setTaskName(taskName);
    }

    public String printTaskDescription() {
        String taskNameAndStatus;
        if (getDone() == true) {
            taskNameAndStatus = DONE_SYMBOL;
        } else {
            taskNameAndStatus = NOT_DONE_SYMBOL;
        }
        taskNameAndStatus = taskNameAndStatus + getTaskName();
        return taskNameAndStatus;
    }

    public String setDone(boolean isDone) {
        this.isDone = isDone;
        String taskStatusSymbol;
        if (isDone == true) {
            taskStatusSymbol = DONE_SYMBOL;
        } else {
            taskStatusSymbol = NOT_DONE_SYMBOL;
        }
        return taskStatusSymbol;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

}
