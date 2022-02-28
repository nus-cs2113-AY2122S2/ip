package duke.task;

/**
 * Represents a task of type "Todo". A <code>Todo</code> object corresponds to
 * a task represented by a description and has no assigned time period to adhere to.  e.g., <code>homework</code>
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description, "T");
    }
}
