package duke;

/**
 * Represents Event subclass
 */
public class Event extends Task{
    /**
     * Create new Event task
     * @param description description of Event task
     */
    public Event(String description){
        super(description);
        this.description = addInDate(description);
        symbol = "[E]";
    }

    /**
     * Create new Event task with specification of whether it is done
     * @param description description of Event task
     * @param isDone status of completion for Event task
     */
    public Event(String description, boolean isDone){
        super(description, isDone);
        this.description = addInDate(description);
        symbol = "[E]";
    }

    /**
     * Interprets the user input and formats the specified event date into description accordingly
     * @param task description of task with the details of event
     * @return formatted string with event date
     */
    public String addInDate(String task){
        int marker = task.indexOf("/");
        String date;
        String description;
        description = task.substring(0,marker);
        date = task.substring(marker+3);

        return description + "(at:" + date + ")";
    }
}
