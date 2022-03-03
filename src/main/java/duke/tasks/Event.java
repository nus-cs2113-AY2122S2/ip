package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Queue;

public class Event extends Task {

    private static final String EVENT_TOSTRING_FORMAT_STRING = "[%c][%c] %s (at: %s)";
    private static final String DATE_FORMAT = "d/M/y";
    private static final String TIME_FORMAT = "HHmm";
    private static final String AT_DATE_STRING_FORMAT = "%s %s-%s";

    protected LocalDateTime atDateTimeStart;
    protected LocalDateTime atDateTimeEnd;

    /**
     * Create an Event with isDone set to false, description of choice and atDate of choice
     *
     * @param description Description of the Task to be created
     * @param atDate String representing event time
     */
    public Event(String description, LocalDateTime atDateTimeStart, LocalDateTime atDateTimeEnd) {
        this.description = description;
        this.isDone = false;
        this.atDateTimeStart = atDateTimeStart;
        this.atDateTimeEnd = atDateTimeEnd;
        this.tag = 'E';
    }

    /**
     * Returns the atDate associated with event
     *
     * @return byDate
     */
    public LocalDateTime getAtDateTimeStart() {
        return this.atDateTimeStart;
    }

    /**
     * Returns the atDate associated with event
     *
     * @return byDate
     */
    public LocalDateTime getAtDateTimeEnd() {
        return this.atDateTimeEnd;
    }

    @Override
    public String toString() {
        return String.format(EVENT_TOSTRING_FORMAT_STRING, this.getTag(), this.getIsDone(), this.getDescription(), this.getAtDateTimeAsString());
    }

    @Override
    public void getFileWriterFormatString(Queue<String> infoToWrite) {
        super.getFileWriterFormatString(infoToWrite);
        infoToWrite.add(getAtDateTimeAsString());
    }

    public String getAtDateTimeAsString() {
        DateTimeFormatter atDateFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
        DateTimeFormatter atTimeFormat = DateTimeFormatter.ofPattern(TIME_FORMAT);
        String getAtDateString = this.atDateTimeStart.format(atDateFormat);
        String getAtTimeStartString = this.atDateTimeStart.format(atTimeFormat);
        String getAtTimeEndString = this.atDateTimeEnd.format(atTimeFormat);
        String getAtDateTimeString = String.format(AT_DATE_STRING_FORMAT, getAtDateString, getAtTimeStartString, getAtTimeEndString);
        return getAtDateTimeString;
    }
}
