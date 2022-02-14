package duke.task;
/**
 * Atask that the user has to do
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Constructs the task
     *
     * @param description
     *            description of the task
     */
    public Task(String descrition)
    {
        this.description=descrition;
        this.isDone=false;
    }
    /**
     * Constructs the task
     *
     * @param description
     *            description of the task
     * @param isDone
     *            status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    /**
     * Get icon representation of the task status
     *
     * @return String representation of the icon
     */

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }
    /**
     * Setter for done status
     *
     * @param isDone
     *            status to set the task to
     */
    public void setDone(boolean isDone)
    {
        if (isDone){
            System.out.println("Nice work!I've marked this task as done:");
            this.isDone=true;
        }else {
            System.out.println("OK, I've marked this task as not done yet:");
            this.isDone=false;
        }
    }
    /**
     * Getter for the task
     *
     * @return string representation of the task
     */
    public String getTask(){
        return "["+this.getStatusIcon()+"]"+this.description;
    }
}
