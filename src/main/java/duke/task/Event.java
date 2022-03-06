package duke.task;

public class Event extends Task {
    protected String eventDate;

    public Event(String description, String eventDate){
        super(description);
        this.eventDate = eventDate;
    }
    public Event(String description, boolean isTaskDone, String eventDate) {
        super(description, isTaskDone);
        this.eventDate = eventDate;
    }
    public String getDescription(){
        return "[E]" + super.getDescription() + " (by: " + eventDate + ")";
    }

}
