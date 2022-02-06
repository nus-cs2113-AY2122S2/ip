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
}
