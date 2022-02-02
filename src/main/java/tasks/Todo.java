package tasks;

/**
 * Todo class is a specialisation of the Task class that only stores a task.
 */
public final class Todo extends Task {
    public Todo(String task) {
        super("T", task);
    }
}
