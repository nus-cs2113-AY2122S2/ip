package duke.command;

import duke.exception.*;
import duke.task.TaskType;

/**
 * Converts the users input into a AddTaskCommand object.
 * An AddTaskCommand object contains the name of the task to add as well as its requirements and type.
 * Examples of requirements of a task are the task's deadlines for tasks with deadlines.
 * Examples of task types are DeadLine, Event, Todo.
 */
public class AddTaskCommand extends Command {
    private String taskRequirement;
    private String taskName;
    private TaskType taskType;

    public AddTaskCommand(String userInput) throws DukeException {
        super(CommandType.ADDTASK);
        try {
            taskType = extractTaskType(userInput);
            taskName = extractTaskName(userInput, taskType);
            taskRequirement = extractTaskRequirement(userInput, taskType);
        } catch (DukeException de) {
            throw de;
        }
    }

    /**
     * Returns the type of task the user wants to add such as DeadLine, Event or Todo from the user's input.
     *
     * @param userInput A string input from the user.
     * @return TaskType Type of task the user wants to add into the task list.
     */
    private TaskType extractTaskType(String userInput) {
        if (userInput.startsWith("deadline")) {
            return TaskType.DEADLINE;
        } else if (userInput.startsWith("event")) {
            return TaskType.EVENT;
        } else {
            return TaskType.TODO;
        }
    }

    /**
     * Returns the name of the task the user wants to add from the user's input.
     *
     * @param userInput A string input from the user.
     * @param type      Task Type of the task the user wants to add.
     * @return The name of the task the user wants to add.
     * @throws DukeException If the task name is not found within the user input or if the command is invalid due to it
     *                       not following the correct formatting.
     */
    private String extractTaskName(String userInput, TaskType type) throws DukeException {
        String taskName;
        int taskNameEndingPosition;
        if (type == TaskType.EVENT) {
            userInput = userInput.replace("event", "");
            if (userInput.trim().isEmpty()) {
                throw new DukeException(DukeExceptionCause.EVENTTASKNAMEEMPTY);
            }
            taskNameEndingPosition = userInput.indexOf("/at");
            if (taskNameEndingPosition == -1) {
                throw new DukeException(DukeExceptionCause.INVALIDCOMMAND);
            }
            taskName = userInput.substring(0, taskNameEndingPosition);
        } else if (type == TaskType.DEADLINE) {
            userInput = userInput.replace("deadline", "");
            if (userInput.trim().isEmpty()) {
                throw new DukeException(DukeExceptionCause.DEADLINETASKNAMEEMPTY);
            }
            taskNameEndingPosition = userInput.indexOf("/by");
            if (taskNameEndingPosition == -1) {
                throw new DukeException(DukeExceptionCause.INVALIDCOMMAND);
            }
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

    /**
     * Returns the requirements of the task the user wants to add
     * such as the task's deadline for tasks with a deadline from the user's input.
     *
     * @param userInput A string input from the user.
     * @param type      Type of task the user wants to add.
     * @return The requirements of the task.
     * @throws DukeException If the task requirement is not found for tasks requiring tasks requirements.
     */
    private String extractTaskRequirement(String userInput, TaskType type) throws DukeException {
        String extractedRequirement = "";
        String trimmedUserInput = userInput.trim();
        int taskRequirementStartPosition;
        int userInputEndingPosition = trimmedUserInput.length();
        if (type == TaskType.EVENT) {
            taskRequirementStartPosition = trimmedUserInput.indexOf("/at") + 3;
            if (taskRequirementStartPosition == userInputEndingPosition) {
                throw new DukeException(DukeExceptionCause.INVALIDCOMMAND);
            }
            extractedRequirement = trimmedUserInput.substring(taskRequirementStartPosition, trimmedUserInput.length());
        } else if (type == TaskType.DEADLINE) {
            taskRequirementStartPosition = trimmedUserInput.indexOf("/by") + 3;
            if (taskRequirementStartPosition == userInputEndingPosition) {
                throw new DukeException(DukeExceptionCause.INVALIDCOMMAND);
            }
            extractedRequirement = trimmedUserInput.substring(taskRequirementStartPosition, trimmedUserInput.length());
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
