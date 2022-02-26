package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "todo";
    }

    @Override
    public String getTime() {
        return "";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
