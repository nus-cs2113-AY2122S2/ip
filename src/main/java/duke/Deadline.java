package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Todo {
    protected LocalDate by;
    public String taskKind;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.taskKind = "[D]";
    }

    @Override
    public String toString(){
        String indicator;
        if (this.isDone){
            indicator = "[X]";
        } else indicator = "[ ]";
        String message = "[D]" + indicator + description
                + " (by: " + getBy() + ")";
        return message;
    }
    public void setBy(String by) {
        this.by = LocalDate.parse(by);
    }

    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
