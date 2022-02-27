package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate by;

    public Deadline(String name, LocalDate by){
        super(name);
        this.by = by;
        setListName();
    }

    public String getTime() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public void setListName(){
        if(!isDone){
            this.listName = "[D]" + this.unmarkedStatus + this.taskName + "(by: " + this.getTime() + ")";
        }else{
            this.listName = "[D]" + this.markedStatus + this.taskName + "(by: " + this.getTime() + ")";
        }
    }

}
