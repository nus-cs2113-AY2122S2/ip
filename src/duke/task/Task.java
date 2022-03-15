package duke.task;

/**
 * Represents a generic task object
 */
public class Task {
    private String name;
    private boolean isMarked;

    public String getName(){
        return name;
    }

    public boolean getMarked(){
        return isMarked;
    }

    public void setMarked(boolean marked){
        this.isMarked = marked;
    }

    public Task(String name, boolean isMarked) {
        this.name = name;
        this.isMarked = isMarked;
    }

    /**
     * Converts the task object into string format
     * @return a string representation of the task object
     */
    public String toString() {
        return " [ ][ ] " + getName();
    }

    /**
     * Converts the task object into string format for storage
     * @return a string representation of the task object to be stored
     */
    public String getTaskDetails() {
        return "task | " + (getMarked() ? 1:0) + " | " + getName() + "\n";
    }
}
