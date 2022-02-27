package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class Event extends Task {
    LocalDate at;

    public Event(String name, LocalDate at) {
        super(name);
        this.at = at;
        setListName();
    }

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM  d yyyy");
        String time = this.at.format(formatter);
        System.out.println(time);
        return time;
    }

    @Override
    public void setListName() {
        if(!isDone){
            this.listName = "[E]" + this.unmarkedStatus + this.taskName + "(at: " + getTime() + ")";
        }else{
            this.listName = "[E]" + this.markedStatus + this.taskName + "(at: " + getTime() + ")";
        }
    }
}
