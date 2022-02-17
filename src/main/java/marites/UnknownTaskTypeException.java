package marites;

public class UnknownTaskTypeException extends MaritesException {
    private final String taskType;

    public UnknownTaskTypeException(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskType() {
        return taskType;
    }
}
