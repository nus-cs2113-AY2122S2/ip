package duke.task;

public class Event extends DynamicTask {
    public Event(String description,String eventTime){
        super(description,"E",eventTime);
    }

    public void setEventTime(String eventTime) {
        this.setTime(eventTime);
    }

    public String getEventTime() {
        return this.getTime();
    }


    // overriding method toString in Object class.
    public String toString(){
        return super.toString()+" (at: "+this.getTime()+")";
    }
}