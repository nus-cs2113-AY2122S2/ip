package marites.exception;

public class TaskListOutOfBoundsException extends MaritesException {
    private int index;

    public TaskListOutOfBoundsException() {
        super();
    }
    @Override
    public String getErrorMessage() {
        return "That index is out of bounds.";
    }
}
