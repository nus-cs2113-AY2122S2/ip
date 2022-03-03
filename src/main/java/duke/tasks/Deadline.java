package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Queue;

public class Deadline extends Task {

    // toString format string
    private static final String DEADLINE_TOSTRING_FORMAT_STRING = "[%c][%c] %s (by: %s)";
    private static final String BY_DATE_DATETIME_FORMAT = "d/M/y HHmm";

    protected LocalDateTime byDateTime;

    /**
     * Create a Deadline with isDone set to false, description of choice and byDate of choice
     *
     * @param description Description of the Task to be created
     * @param byDateTime String representing deadline
     */
    public Deadline(String description, LocalDateTime byDateTime) {
        this.description = description;
        this.isDone = false;
        this.byDateTime = byDateTime;
        this.tag = 'D';
    }

    /**
     * Returns the byDate associated with deadline
     *
     * @return byDate
     */
    public LocalDateTime getByDateTime() {
        return this.byDateTime;
    }

    @Override
    public String toString() {
        return String.format(DEADLINE_TOSTRING_FORMAT_STRING, this.getTag(), this.getIsDone(), this.getDescription(), this.getByDateTimeAsString());
    }

    @Override
    public void getFileWriterFormatString(Queue<String> infoToWrite) {
        super.getFileWriterFormatString(infoToWrite);
        infoToWrite.add(getByDateTimeAsString());
    }

    public String getByDateTimeAsString() {
        DateTimeFormatter byDateTimeFormat = DateTimeFormatter.ofPattern(BY_DATE_DATETIME_FORMAT);
        String getByDateTimeString = this.byDateTime.format(byDateTimeFormat);
        return getByDateTimeString;
    }
}
