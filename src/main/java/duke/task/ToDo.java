package duke.task;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task{

    public ToDo(String description, String typeOfTask) {
        super(description, typeOfTask);
    }

    /**
     * This is the toString method that returns a particular format to represent this ToDo task.
     *
     * @return The printing format of this ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
