public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter for task description
     *
     * @return description: ArrayList of tasks in the saved file
     */
    public String getDescription(){
        return description;
    }

    /**
     * Getter for string for saving the tasks into the file.
     *
     * @return formatted string representing the task
     */
    public String getString(){
        String done = "0";
        if (isDone){
            done = "1";
        }
        return ("T," + done + "," + description);
    }

    /**
     * Getter for Done status
     *
     * @return "X" if done, " " if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Setter for Done status
     *
     * @param done
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Print method for printTask() to call
     *
     */
    public void printTask(){
        System.out.println("[" + this.getStatusIcon() + "] " + this.description);
    }
}