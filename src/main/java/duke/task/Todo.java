package duke.task;

public class Todo extends Task{

    public Todo(String description,int taskUniqueID) {
        super(description, taskUniqueID);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
