package tasks;

/**
 * Todo class is a specialisation of the Task class that only stores a task.
 */
public final class Todo extends Task {
    /**
     * Creates a new Todo object.
     *
     * @param task   Todo task.
     * @param isDone Whether the task is done.
     */
    public Todo(String task, boolean isDone) {
        super("T", isDone, task);
    }

    /**
     * Attempts to unmarshal a storage-friendly parts string into a Todo object.
     *
     * @param parts A storage-friendly string split into parts.
     * @return Parsed Deadline object.
     */
    public static Todo unMarshal(String[] parts) {
        return new Todo(parts[2], Boolean.parseBoolean(parts[1]));
    }
}
