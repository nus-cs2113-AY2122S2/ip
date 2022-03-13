package util.task;

/**
 * Represents a type of task that the user needs to do but can do anytime
 */
public class Todo extends Task {
    /**
     * Initialize a todo object
     * @param task The task user adds
     */
    public Todo(String task) {
        super(task);
        this.typeOfTask = "[T]";
    }
}
