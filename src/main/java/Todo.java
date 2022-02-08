public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public void printDescription() {
        if (isDone) {
            System.out.println("[T][X] " + description);
        } else {
            System.out.println("[T][ ] " + description);
        }
    }
}
