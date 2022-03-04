package marites.task;

/**
 * A sub-class of Task for to-do items, which may be done at any time.
 */
public class Todo extends Task {

    private static final String TODO_FORMAT_STRING = "[T]%s";

    public Todo(String description) {
         super(description);
    }

    @Override
    public String toString() {
        return String.format(TODO_FORMAT_STRING, super.toString());
    }
}
