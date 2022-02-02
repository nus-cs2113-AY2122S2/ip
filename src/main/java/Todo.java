public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String marking = "[T]";
        return marking + super.toString();
    }
}
