
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        // return "[D]" + super.toString() + "(" + by + ")";
        return "[D]" + (super.isDone ? "[X]" + super.description : "[ ]"+ super.description) + "(" + by + ")" ; // mark done task with X

     }
}