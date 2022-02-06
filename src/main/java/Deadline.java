public class Deadline extends Task {

    // toString format string
    public static final String FORMAT_STRING = "[D][%c] %s (by: %s)";

    protected String byDate;

    /**
     * Create a Deadline with isDone set to false, empty description and empty byDate
     */
    public Deadline() {
        super();
        this.byDate = "";
    }

    /**
     * Create a Deadline with isDone set to false, description of choice and empty byDate
     *
     * @param description Description of the Task to be created
     */
    public Deadline(String description) {
        super(description);
        this.byDate = "";
    }

    /**
     * Create a Deadline with isDone set to false, description of choice and byDate of choice
     *
     * @param description Description of the Task to be created
     * @param byDate String representing deadline
     */
    public Deadline(String description, String byDate) {
        this.description = description;
        this.isDone = false;
        this.byDate = byDate;
    }

    /**
     * Returns the byDate associated with deadline
     *
     * @return byDate
     */
    public String getByDate() {
        return byDate;
    }

    @Override
    public String toString() {
        return String.format(FORMAT_STRING, this.getStatusIcon(), this.getDescription(), this.getByDate());
    }
}
