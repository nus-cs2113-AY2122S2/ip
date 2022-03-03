/**
 * Represents a <code>Todo</code> object on a person's list of tasks that they must complete.
 * This class inherits from the Task class and supports a slightly different toString() method
 * that contains a marker for the type of Task the Todo object is (a "T" for "Todo").
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}