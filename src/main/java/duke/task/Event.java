package duke.task;

public class Event extends Task {
    private String eventTime;

    public Event(String description,String eventTime){
        super(description,"E");
        this.eventTime=eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    // overriding method toString in Object class.
    public String toString(){
        return super.toString()+" (at: "+eventTime+")";
    }
}