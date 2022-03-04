package Duke.Tasks;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a todo object.
     *
     * @param description The description of event to be done.
     * @param isDone Whether the task has been done
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns todo as a readable string.
     *
     * @return todo as a readable string.
     */
    @Override
    public String toString () {
        return "[T]" + super.toString();
    }

    /**
     * Saves the tasks in the list as a certain format.
     *
     * @return String to be saved.
     */
    @Override
    public String saveTasks() {
        return "T " + super.saveTasks() + System.lineSeparator();
    }
}
