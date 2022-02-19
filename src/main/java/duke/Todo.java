package duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return " [T][" + this.getStatusIcon() + "] " + this.description;
    }

    //@@author quitejasper-reused
    //Reused from https://github.com/FaliciaOng/ip/blob/master/src/main/java/Todo.java
    //with minor modifications
    @Override
    public String toFileString() {
        return "T " + super.toFileString();
    }
    //@@author
}
