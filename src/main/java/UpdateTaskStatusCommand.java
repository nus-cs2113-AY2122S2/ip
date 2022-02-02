public class UpdateTaskStatusCommand extends Command {
    private int taskIndex;
    private boolean isTaskDone;

    public UpdateTaskStatusCommand(String userInput, boolean isTaskDone) {
        super(CommandType.UPDATETASKSTATUS);
        this.taskIndex = extractTaskIndex(userInput, isTaskDone);
        this.isTaskDone = isTaskDone;
    }

    public int extractTaskIndex(String userInput, boolean isTaskDone) {
        String taskIndexStringVersion;
        int taskIndexStartPosition;
        if (isTaskDone == true) {
            taskIndexStartPosition = 5;
        } else {
            taskIndexStartPosition = 7;
        }
        taskIndexStringVersion = userInput.substring(taskIndexStartPosition, userInput.length());
        return Integer.parseInt(taskIndexStringVersion) - 1;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public boolean isTaskDone() {
        return isTaskDone;
    }
}
