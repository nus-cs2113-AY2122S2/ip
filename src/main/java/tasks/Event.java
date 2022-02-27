package tasks;

import time.TimeToStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import time.*;

public class Event extends Task {
    protected Date at;
    protected String atString;
    Time timeConverter;

    public Event(String name, String atString) throws Exception{
        super(name);
        this.atString = atString;
        timeConverter = new Time(atString);
        at = timeConverter.getNewDate();
        //System.out.println(this.at.toString());
        setListName();
    }

    @Override
    public void setListName() {
        if(!isDone){
            this.listName = "[E]" + this.unmarkedStatus + this.taskName + "(at: " + atString + ")";
        }else{
            this.listName = "[E]" + this.markedStatus + this.taskName + "(at: " + atString + ")";
        }
    }


}
