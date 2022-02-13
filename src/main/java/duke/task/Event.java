package duke.task;

public class Event extends Task {

    public void setAt(String at) {
        this.at = at;
    }

    protected String at;
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.taskType = 'E';
    }

    public String toString() {

        return "[E]" + super.toString() + " (at:" + at + ")";
    }

    public String saveDescription() {
        return super.saveDescription();
    }

    public String saveAdditionalInfo() {
        return "/at" + at;
    }
}
