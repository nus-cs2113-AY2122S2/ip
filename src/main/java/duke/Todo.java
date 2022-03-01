package duke;

/**
 * Represents Todo subclass
 */
public class Todo extends Task{
    /**
     * Create new Todo task
     * @param description description of Todo task
     */
    public Todo(String description){
        super(description);
        symbol = "[T]";
    }

    /**
     * Create new Todo task with specification of whether it is done
     * @param description description of Todo task
     * @param isDone status of completion for Todo task
     */
    public Todo(String description, boolean isDone){
        super(description, isDone);
        symbol = "[T]";
    }
}
