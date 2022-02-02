public class Todo extends Task {
    private static final String TODO_CHECKBOX = "[T]";

    public Todo(String description) {
        super(description);
    }

    public String toString() {
        String checkbox = getCheckbox();
        return String.format("%s%s %s", TODO_CHECKBOX, checkbox, getDescription());
    }
}
