public class Todo extends Task {

    // toString format string
    public static final String FORMAT_STRING = "[T][%c] %s";

    /**
     * Create a Todo with isDone set to false and description of choice
     *
     * @param description Description of the Task to be created
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format(FORMAT_STRING, this.getStatusIcon(), this.getDescription());
    }
}
