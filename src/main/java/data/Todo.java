package data;

/**
 * Represents a todo task with only task description.
 */
public class Todo extends Task {
    /**
     * Class constructor.
     *
     * @param description description of the todo task
     */
    public Todo(String description) {

        super(description);
    }

    /**
     * Class constructor specifying whether the task is done.
     *
     * @param description description of the todo task
     * @param isDone whether the task is done
     */
    public Todo(String description, boolean isDone) {

        super(description, isDone);
    }

    @Override
    public String toString() {

        return "[T]" + super.toString();
    }

    @Override
    public String getInfo(){
        return "T / " + super.isDone() + " / " + super.getDescription();
    }
}
