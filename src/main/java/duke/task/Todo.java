package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getTaskType() {
        return "todo";
    }

    @Override
    public String getDateFormattedString() {
        return "-";
    }

    @Override
    public String getDateForStorage() {
        return "-";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
