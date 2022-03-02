package tasks;

import java.util.Date;

import time.Time;

public class Event extends Task {
    protected Date at;
    protected String atString;
    private Time timeConverter;

    /**
     * Initializes an event task by given task name and event time
     * @param name the name of the event task
     * @param atString the time of the task in String format
     */
    public Event(String name, String atString) {
        super(name);
        this.atString = atString;
        timeConverter = new Time(atString);
        at = timeConverter.getNewDate();
        setListName();
    }

    @Override
    public void setListName() {
        if (!isDone) {
            this.listName = "[E]" + this.unmarkedStatus + this.taskName + "(at: " + atString + ")";
        } else {
            this.listName = "[E]" + this.markedStatus + this.taskName + "(at: " + atString + ")";
        }
    }


}
