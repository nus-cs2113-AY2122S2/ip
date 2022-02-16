package duke.task;

public class Todo extends Task {

    protected final String type = "T";

    public Todo(String description) {
        super(description.trim());
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
