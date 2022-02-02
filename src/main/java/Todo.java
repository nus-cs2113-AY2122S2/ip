public class Todo extends Task {

    public Todo(String description) {
        super(description);
        System.out.println("\nSuccessfully added to list:\n" + addIndentation() + "[T]" + getStatusIcon() + " "
                + description);
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + super.toString();
    }
}
