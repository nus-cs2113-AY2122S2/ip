package tasks;

/**
 * This class handles creation of Todo instances.
 */
public class Todo extends Task {
    private static final String TASK_CODE = "T";

    /**
     * Creates a Todo instance with only a task description.
     *
     * @param taskDescription Description of the todo task
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Creates a Todo instance with a task description and its completion status.
     *
     * @param taskDescription Description of the todo task
     * @param isCompleted Completion status of the task (ie true if done; false otherwise)
     */
    public Todo(String taskDescription, boolean isCompleted) {
        super(taskDescription, isCompleted);
    }

    /**
     * @inheritDocs
     */
    @Override
    public String getTaskCode() {
        return TASK_CODE;
    }

    /**
     * @inheritDocs
     */
    @Override
    public String getExtraInfo() {
        return "";
    }

    /**
     * @inheritDocs
     */
    @Override
    public String toString() {
        return "[" + getTaskCode() + "]" + super.toString();
    }
}
