package duke.task;

/**
 * Represents a Todo object. A Task without any deadlines or timings
 */
public class Todo extends Task {
    public Todo(String name, boolean marked){
        super(name,marked);
    }

    /**
     * Converts the Todo object into string format
     * @return a string representation of the Todo object
     */
    public String toString() {
        if (super.getMarked()) {
            return " [T][X] " + getName();
        }else {
            return " [T][ ] " + getName();
        }
    }

    /**
     * Converts the Todo object into string format for storage
     * @return a string representation of the Todo object to be stored
     */
    public String getTaskDetails() {
        return "todo | " + (getMarked() ? 1:0) + " | " + getName() + "\n";
    }
}
