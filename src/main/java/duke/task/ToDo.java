package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String getTask() {
        return "[T]" + super.getTask();
    }
}
