package duke.task;
public class Event extends Task{
    protected String eventTime;
    public Event(String name, boolean marked, String eventTime){
        super(name, marked);
        this.eventTime = eventTime;
    }

    public String getEventTime(){
        return eventTime;
    }

    public String toString(){
        if (super.getMarked()){
            return " [E][X] " + getName() + " (at: " + getEventTime() + ")";
        }else{
            return " [E][ ] " + getName() + " (at: " + getEventTime() + ")";
        }
    }
    public String getTaskDetails(){
        return "E | " + (getMarked() ? 1:0) + " | " + getName() + " | " + getEventTime() + "\n";
    }
}
