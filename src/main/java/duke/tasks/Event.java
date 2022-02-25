package duke.tasks;

/**
 * Represents a Event in task list.
 */

public class Event extends Task{
    protected String startEndTime;

    public Event(String description, String startEndTime) {
        super(description);
        this.startEndTime = startEndTime;
    }

    public String toString(){
        int index = startEndTime.indexOf(" ");
        String time = startEndTime.substring(0,index) + ": " +startEndTime.substring(index+1);
        return "[E]" + super.toString() + "(" + time + ")";
    }

    public String getCategory(){
        return "E";
    }

    public String getStartEndTime() {
        return startEndTime;
    }
}
