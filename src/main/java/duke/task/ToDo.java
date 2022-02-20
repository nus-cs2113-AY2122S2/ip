package duke.task;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task{
    private static final String TODO_LOGO = "[T]";

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
        return TODO_LOGO + super.toString();
    }
}
