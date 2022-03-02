package duke;

/**
 * Parent class of all kinds of tasks
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String symbol;

    /**
     *  Creates new task with specified description. Used as super constructor.
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Overloaded constructor with additional specification of whether task is completed
     * @param description description of task
     * @param isDone status of completion of task
     */
    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Generates status icon of task based on whether it is done
     * @return status icon
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Getter for symbol of task
     * @return symbol of task
     */
    public String getSymbol(){
        return symbol;
    }

    /**
     * Getter for description of task
     * @return description of task
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Getter of status of completion of task
     * @return whether task is done or not
     */
    public boolean getIsDone(){
        return isDone;
    }

    /**
     * Sets isDone to true
     */
    public void markAsDone(){
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
    }

    /**
     * Sets isDone to false
     */
    public void markAsUndone(){
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
    }

    /**
     * Generates description of task
     * @return string of description of task
     */
    public String printTask(){
        return "\t" + getSymbol() + getStatusIcon() + getDescription();
    }


}
