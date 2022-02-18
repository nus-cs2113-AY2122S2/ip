package duke.tasks;

import java.util.Queue;

public class Deadline extends Task {

    // toString format string
    private static final String FORMAT_STRING = "[%c][%c] %s (by: %s)";

    protected String byDate;

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
        this.tag = 'D';
    }

    /**
     * Returns the byDate associated with deadline
     *
     * @return byDate
     */
    public String getByDate() {
        return this.byDate;
    }

    @Override
    public String toString() {
        return String.format(FORMAT_STRING, this.getTag(), this.getIsDone(), this.getDescription(), this.getByDate());
    }

    @Override
    public void getFileWriterFormatString(Queue<String> infoToWrite) {
        super.getFileWriterFormatString(infoToWrite);
        infoToWrite.add(this.byDate);
    }
}
