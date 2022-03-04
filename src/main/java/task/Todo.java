package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Prints out the todo in a custom format.
     * @return String to be printed.
     */
    @Override
    public String toString() {
        return " [T][" + this.getStatusIcon() + "] " + this.description;
    }

    //@@author quitejasper-reused
    //Reused from https://github.com/FaliciaOng/ip/blob/master/src/main/java/Todo.java
    //with minor modifications

    /**
     * converts the todo into a string that is to be written back into
     * the text document for storage.
     * @return String to be written back into storage.
     */
    @Override
    public String toFileString() {
        return "T " + super.toFileString();
    }
    //@@author
}
