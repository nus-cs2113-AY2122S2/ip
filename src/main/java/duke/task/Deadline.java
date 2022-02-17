package duke.task;

public class Deadline extends DynamicTask {

    public Deadline(String description, String dueDate) {
        super(description, "D", dueDate);
    }

    public void setDueDate(String dueDate) {
        this.setTime(dueDate);
    }

    public String getDueDate() {
        return this.getTime();
    }

    // overriding method toString in Object class
    public String toString() {
        return super.toString() + " (by: " + this.getTime() + ")";
    }
}
