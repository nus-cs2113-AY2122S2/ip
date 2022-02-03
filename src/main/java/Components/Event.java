package Components;

public class Event extends Task{
    String dateTime;

    public Event(String description, String dateTime){
        super(description);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String toString(){
        return "[E]" +
                "[" + getStatusIcon() + "] " + description +
                " (at: " + getDateTime() + ")";
    }
}
