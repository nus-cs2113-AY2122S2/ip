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
        if (isTaskDone == true) {
            taskIndexStringVersion = userInput.substring(5, userInput.length());
        } else {
            taskIndexStringVersion = userInput.substring(7, userInput.length());
        }
        return Integer.parseInt(taskIndexStringVersion) - 1;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public boolean isTaskDone() {
        return isTaskDone;
    }
}
