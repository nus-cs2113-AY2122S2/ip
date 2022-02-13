package duke.task;

public class Deadline extends Task {

    public void setBy(String by) {
        this.by = by;
    }

    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = 'D';
    }

    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    public String saveDescription() {
        return super.saveDescription();
    }
    public String saveAdditionalInfo() {
        return "/by" + by;
    }
}
