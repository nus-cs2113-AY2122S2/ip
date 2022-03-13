package duke.task;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task{

    public ToDo(String description, String typeOfTask) {
        super(description, typeOfTask);
    }

    /**
     * Returns a particular format to represent this todo task.
     *
     * @return The printing format of this todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
