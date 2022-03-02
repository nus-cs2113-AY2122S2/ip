package tasks;

import java.util.Date;

import time.Time;

public class Deadline extends Task {
    protected Date by;
    protected String byString;
    private Time timeConverter;

    /**
     * Initializes a deadline task by given task name and deadline
     * @param name the name of the task
     * @param byString the deadline of the task in String format
     */
    public Deadline(String name, String byString) {
        super(name);
        this.byString = byString;
        timeConverter = new Time(byString);
        by = timeConverter.getNewDate();
        setListName();
    }

    @Override
    public void setListName() {
        if (!isDone) {
            this.listName = "[D]" + this.unmarkedStatus + this.taskName + "(by: " + byString + ")";
        } else {
            this.listName = "[D]" + this.markedStatus + this.taskName + "(by: " + byString + ")";
        }
    }

}
