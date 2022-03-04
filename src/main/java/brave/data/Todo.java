package brave.data;

/**
 * Inherit from Task class
 * Represent the type of task To-Do that only have description
 */
public class Todo extends Task {
    /**
     * Class constructor specifying whether the task is done.
     *
     * @param description description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getSaveFormat() {
        if (!isDone) {
            return "T , 0 , " + description;
        } else {
            return "T , 1 , " + description;
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}