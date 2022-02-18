package duke.tasks;

import java.util.Queue;

public class Event extends Task {

    private static final String FORMAT_STRING = "[%c][%c] %s (at: %s)";

    protected String atDate;

    /**
     * Create an Event with isDone set to false, description of choice and atDate of choice
     *
     * @param description Description of the Task to be created
     * @param atDate String representing event time
     */
    public Event(String description, String atDate) {
        this.description = description;
        this.isDone = false;
        this.atDate = atDate;
        this.tag = 'E';
    }

    /**
     * Returns the atDate associated with event
     *
     * @return byDate
     */
    public String getAtDate() {
        return this.atDate;
    }

    @Override
    public String toString() {
        return String.format(FORMAT_STRING, this.getTag(), this.getIsDone(), this.getDescription(), this.getAtDate());
    }

    @Override
    public void getFileWriterFormatString(Queue<String> infoToWrite) {
        super.getFileWriterFormatString(infoToWrite);
        infoToWrite.add(atDate);
    }
}
