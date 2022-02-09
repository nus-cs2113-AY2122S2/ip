package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + super.toString();
    }

    public void printAddToListMessage() {
        System.out.println("\nSuccessfully added to list:\n" + addIndentation() + "[T]" + getStatusIcon() + " "
                + description);
    }
}
