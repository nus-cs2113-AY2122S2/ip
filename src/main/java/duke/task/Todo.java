package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    public String getDate() {
        return "-";
    }

    @Override
    public String getTaskType() {
        return "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
