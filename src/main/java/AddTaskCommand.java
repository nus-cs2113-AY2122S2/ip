public class AddTaskCommand extends Command {
    private String taskRequirement;
    private String taskName;
    private TaskType taskType;


    public AddTaskCommand(String userInput) {
        super(CommandType.ADDTASK);
        taskType = extractTaskType(userInput);
        taskName = extractTaskName(userInput, taskType);
        taskRequirement = extractTaskRequirement(userInput, taskType);
    }

    public TaskType extractTaskType(String userInput) {
        if (userInput.contains("deadline")) {
            return TaskType.DEADLINES;
        } else if (userInput.contains("event")) {
            return TaskType.EVENTS;
        } else {
            return TaskType.TODO;
        }
    }

    public String extractTaskName(String userInput, TaskType type) {
        String taskName;
        int taskNameEndingPosition;
        if (type == TaskType.EVENTS) {
            userInput = userInput.replace("event", "");
            taskNameEndingPosition = userInput.indexOf("/at");
            taskName = userInput.substring(0, taskNameEndingPosition);
        } else if (type == TaskType.DEADLINES) {
            userInput = userInput.replace("deadline", "");
            taskNameEndingPosition = userInput.indexOf("/by");
            taskName = userInput.substring(0, taskNameEndingPosition);
        } else {
            taskName = userInput.replace("todo", "");
        }
        return taskName.trim();
    }

    public String extractTaskRequirement(String userInput, TaskType type) {
        String extractedRequirement = "";
        int taskRequirementStartPosition;
        if (type == TaskType.EVENTS) {
            taskRequirementStartPosition = userInput.indexOf("/at") + 3;
            extractedRequirement = userInput.substring(taskRequirementStartPosition, userInput.length());
        } else if (type == TaskType.DEADLINES) {
            taskRequirementStartPosition = userInput.indexOf("/by") + 3;
            extractedRequirement = userInput.substring(taskRequirementStartPosition, userInput.length());
        }
        return extractedRequirement.trim();
    }

    public String getTaskRequirement() {
        return taskRequirement;
    }

    public String getTaskName() {
        return taskName;
    }

    public TaskType getTaskType() {
        return taskType;
    }

}
