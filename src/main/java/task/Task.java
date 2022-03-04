package task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task. This method is used for Todo objects.
     * @param description description of the task.
     * @throws NullPointerException when there is no description.
     */
    public Task(String description) throws NullPointerException {
        if (description.isBlank()) {
            throw new NullPointerException();
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method checks the isDone attribute of the task.
     * It will return the status of the task to be used for printing in various methods.
     * @return status icon of the task in the form of a string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * @return boolean value indicating if the task in question is complete or not.
     */
    public boolean getDoneStatus() {
        return this.isDone;
    }

    /**
     * Prints out the task in a custom format.
     * @return String to be printed.
     */
    @Override
    public String toString() {
        return " [" + this.getStatusIcon() + "] " + this.description;
    }

    //@@author quitejasper-reused
    //Reused from https://github.com/FaliciaOng/ip/blob/master/src/main/java/TaskFileManager.java
    //with minor modifications
    /**
     * converts the task into a string that is to be written back into
     * the text document for storage.
     * @return String to be written back into storage.
     */
    public String toFileString() {
        return "| " + getStatusNumber() + " | " + this.description;
    }

    /**
     * converts status of task into string to be written back into text document.
     * @return String of status of task to be written back into storage.
     */
    private int getStatusNumber() {
        return (isDone ? 1 : 0);
    }
    //@@author
}
