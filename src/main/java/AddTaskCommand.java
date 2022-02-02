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
        if (type == TaskType.EVENTS) {
            userInput = userInput.replace("event", "");
            taskName = userInput.substring(0, userInput.indexOf("/at"));
        } else if (type == TaskType.DEADLINES) {
            userInput = userInput.replace("deadline", "");
            taskName = userInput.substring(0, userInput.indexOf("/by"));
        } else {
            taskName = userInput.replace("todo", "");
        }
        return taskName.trim();
    }

    public String extractTaskRequirement(String userInput, TaskType type) {
        String extractedRequirement = "";
        if (type == TaskType.EVENTS) {
            extractedRequirement = userInput.substring(userInput.indexOf("/at") + 3, userInput.length());
        } else if (type == TaskType.DEADLINES) {
            extractedRequirement = userInput.substring(userInput.indexOf("/by") + 3, userInput.length());
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
