package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeExceptionCause;

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
    private final String DEADLINE_TIMING_INDICATOR = "/by";
    private final String EVENT_TIMING_INDICATOR = "/at";

    public AddTaskCommand(String userInput) throws DukeException {
        super(CommandType.AddTaskCommand);
        taskType = extractTaskType(userInput);
        taskName = extractTaskName(userInput, taskType);
        taskRequirement = extractTaskRequirement(userInput, taskType);
    }

    /**
     * Returns the type of task the user wants to add such as DeadLine, Event or Todo from the user's input.
     *
     * @param userInput A string input from the user.
     * @return TaskType Type of task the user wants to add into the task list.
     */
    private TaskType extractTaskType(String userInput) {
        if (userInput.startsWith("deadline")) {
            return TaskType.Deadline;
        } else if (userInput.startsWith("event")) {
            return TaskType.Event;
        } else {
            return TaskType.Todo;
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
        final int STRING_END_POSITION = -1;
        if (type == TaskType.Event) {
            taskName = userInput.replace("event", "");
            taskName = taskName.trim();
            if (taskName.isEmpty() || taskName.indexOf(EVENT_TIMING_INDICATOR) == 0) {
                throw new DukeException(DukeExceptionCause.EventTaskNameEmpty);
            }
            taskNameEndingPosition = taskName.indexOf(EVENT_TIMING_INDICATOR);
            if (taskNameEndingPosition == STRING_END_POSITION) {
                throw new DukeException(DukeExceptionCause.InvalidCommand);
            }
            taskName = taskName.substring(0, taskNameEndingPosition);
        } else if (type == TaskType.Deadline) {
            taskName = userInput.replace("deadline", "");
            taskName = taskName.trim();
            if (taskName.isEmpty() || taskName.indexOf(DEADLINE_TIMING_INDICATOR) == 0) {
                throw new DukeException(DukeExceptionCause.DeadlineTaskNameEmpty);
            }
            taskNameEndingPosition = taskName.indexOf(DEADLINE_TIMING_INDICATOR);
            if (taskNameEndingPosition == STRING_END_POSITION) {
                throw new DukeException(DukeExceptionCause.InvalidCommand);
            }
            taskName = taskName.substring(0, taskNameEndingPosition);
        } else {
            taskName = userInput.replace("todo", "");
            if (taskName.trim().isEmpty()) {
                throw new DukeException(DukeExceptionCause.ToDoTaskNameEmpty);
            }
        }
        taskName = taskName.trim();
        return taskName;
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
        if (type == TaskType.Event) {
            int LENGTH_OF_SLASH_AT = 3;
            taskRequirementStartPosition = trimmedUserInput.indexOf(EVENT_TIMING_INDICATOR) + LENGTH_OF_SLASH_AT;
            if (taskRequirementStartPosition == userInputEndingPosition) {
                throw new DukeException(DukeExceptionCause.InvalidCommand);
            }
            extractedRequirement = trimmedUserInput.substring(taskRequirementStartPosition, trimmedUserInput.length());
        } else if (type == TaskType.Deadline) {
            int LENGTH_OF_SLASH_BY = 3;
            taskRequirementStartPosition = trimmedUserInput.indexOf(DEADLINE_TIMING_INDICATOR) + LENGTH_OF_SLASH_BY;
            if (taskRequirementStartPosition == userInputEndingPosition) {
                throw new DukeException(DukeExceptionCause.InvalidCommand);
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
