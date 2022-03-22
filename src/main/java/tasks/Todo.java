package tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the todo task in the form of a formatted string
     * The string is used to display todo tasks to users
     *
     * @return String form of todo task for display
     * */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    /**
     * Returns the todo task in the form of a formatted string
     * The string is used to write todo tasks to the user's task list file in the user's hard disk
     *
     * @return String form of todo task for writing to task list file
     * */
    @Override
    public String getDetails() {
        return "T|" + (isDone ? "1" : "0") + "|" + description;
    }
}
