package duke;

/**
 * Represents Deadline subclass
 */
public class Deadline extends Task{
    /**
     * Create new Deadline task
     * @param description description of Deadline task
     */
    public Deadline(String description){
        super(description);
        this.description = addInDate(description);
        symbol = "[D]";
    }

    /**
     * Create new Deadline task with specification of whether it is done
     * @param description description of Deadline task
     * @param isDone status of completion for Deadline task
     */
    public Deadline(String description, boolean isDone){
        super(description, isDone);
        this.description = addInDate(description);
        symbol = "[D]";
    }

    /**
     * Interprets the user input and formats the specified deadline into description accordingly
     * @param task description of task with the details of deadline
     * @return formatted string with deadline
     */
    public String addInDate(String task){
        int marker = task.indexOf("/");
        String date;
        String description;
        description = task.substring(0,marker);
        date = task.substring(marker+3);

        return description + "(by:" + date + ")";
    }
}
