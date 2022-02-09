public class AddTaskCommand extends Command {
    private String taskRequirement;
    private String taskName;
    private TaskType taskType;


    public AddTaskCommand(String userInput) throws DukeException {
        super(CommandType.ADDTASK);
        taskType = extractTaskType(userInput);
        try {
            taskName = extractTaskName(userInput, taskType);
        } catch (DukeException de) {
            throw de;
        }
        taskRequirement = extractTaskRequirement(userInput, taskType);
    }

    public TaskType extractTaskType(String userInput) {
        if (userInput.contains("deadline")) {
            return TaskType.DEADLINE;
        } else if (userInput.contains("event")) {
            return TaskType.EVENT;
        } else {
            return TaskType.TODO;
        }
    }

    public String extractTaskName(String userInput, TaskType type) throws DukeException {
        String taskName;
        int taskNameEndingPosition;
        if (type == TaskType.EVENT) {
            userInput = userInput.replace("event", "");
            if (userInput.trim().isEmpty()) {
                throw new DukeException(DukeExceptionCause.EVENTTASKNAMEEMPTY);
            }
            taskNameEndingPosition = userInput.indexOf("/at");
            taskName = userInput.substring(0, taskNameEndingPosition);
        } else if (type == TaskType.DEADLINE) {
            userInput = userInput.replace("deadline", "");
            if (userInput.trim().isEmpty()) {
                throw new DukeException(DukeExceptionCause.DEADLINETASKNAMEEMPTY);
            }
            taskNameEndingPosition = userInput.indexOf("/by");
            taskName = userInput.substring(0, taskNameEndingPosition);
        } else {
            taskName = userInput.replace("todo", "");
            if (taskName.trim().isEmpty()) {
                throw new DukeException(DukeExceptionCause.TODOTASKNAMEEMPTY);
            }
        }
        taskName = taskName.trim();
        return taskName.trim();
    }

    public String extractTaskRequirement(String userInput, TaskType type) {
        String extractedRequirement = "";
        int taskRequirementStartPosition;
        if (type == TaskType.EVENT) {
            taskRequirementStartPosition = userInput.indexOf("/at") + 3;
            extractedRequirement = userInput.substring(taskRequirementStartPosition, userInput.length());
        } else if (type == TaskType.DEADLINE) {
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
