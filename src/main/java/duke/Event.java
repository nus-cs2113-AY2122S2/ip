package duke;

public class Event extends Task{
    public Event(String description){
        super(description);
        this.description = addInDate(description);
        symbol = "[E]";
    }

    public String addInDate(String task){
        int marker = task.indexOf("/");
        String date;
        String description;
        description = task.substring(0,marker);
        date = task.substring(marker+3);

        return description + " (at: " + date + ")";
    }
}
