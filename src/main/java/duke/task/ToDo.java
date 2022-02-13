package duke.task;

public class ToDo extends Task {


    public ToDo(String description) {
        super(description);
        this.taskType = 'T';
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String saveDescription() {
        return super.saveDescription();
    }
    public String saveAdditionalInfo() {
        return "";
    }
}
