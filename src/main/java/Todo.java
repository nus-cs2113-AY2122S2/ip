public class Todo extends Task {

    private final static String TODO_MARKER = "[T]";
    protected String by;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TODO_MARKER + super.toString();
    }
}