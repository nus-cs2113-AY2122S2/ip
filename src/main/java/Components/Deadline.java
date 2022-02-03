package Components;

public class Deadline extends Task{
    String dateTime;

    public Deadline(String description, String dateTime){
        super(description);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String toString(){
        return "[D]" +
                "[" + getStatusIcon() + "] " + description +
                " (by: " + getDateTime() + ")";
    }
}
