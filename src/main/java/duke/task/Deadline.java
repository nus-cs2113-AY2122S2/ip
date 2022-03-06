package duke.task;

public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String description, String dueDate){
        super(description);
        this.dueDate = dueDate;
    }

    public Deadline(String description, boolean isTaskDone, String dueDate) {
        super(description, isTaskDone);
        this.dueDate = dueDate;
    }

    public String getDescription(){
        return "[D]" + super.getDescription() + " (by: " + dueDate + ")";
    }
}
