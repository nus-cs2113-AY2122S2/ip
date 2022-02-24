package duke.task;

public class Todo extends Task{

    public Todo(String description,int taskUniqueID) {
        super(description, taskUniqueID);
    }

    /**
     * Create the description message specific to Todo objects.
     * @return String containing the message
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
