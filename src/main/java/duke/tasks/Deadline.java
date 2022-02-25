package duke.tasks;

/**
 * Represents a Deadline in task list.
 */

public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toString(){
        int index = by.indexOf(" ");
        String time = by.substring(0,index) + ": " +by.substring(index+1);
        return "[D]" + super.toString() + " (" + time + ")";
    }

    public String getCategory(){
        return "D";
    }

    public String getBy() {
        return by;
    }
}
