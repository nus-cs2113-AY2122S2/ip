package tasks;

import time.TimeToStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import time.*;

public class Deadline extends Task {
    protected Date by;
    protected String byString;
    Time timeConverter;

    public Deadline(String name, String byString) throws Exception{
        super(name);
        this.byString = byString;
        timeConverter = new Time(byString);
        by = timeConverter.getNewDate();
        setListName();
    }

    @Override
    public void setListName(){
        if(!isDone){
            this.listName = "[D]" + this.unmarkedStatus + this.taskName + "(by: " + byString + ")";
        }else{
            this.listName = "[D]" + this.markedStatus + this.taskName + "(by: " + byString + ")";
        }
    }

}
