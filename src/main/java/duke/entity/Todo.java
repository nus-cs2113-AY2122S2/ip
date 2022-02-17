package duke.entity;

public class Todo extends Task {

    private final static String TODO_MARKER = "[T]";

    //constructor
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isTaskDone) {
        super(description, isTaskDone);
    }

    @Override
    public String toString() {
        return TODO_MARKER + super.toString();
    }
}