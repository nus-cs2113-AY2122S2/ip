package duke;

public class Todo extends Task{
    public Todo(String description){
        super(description);
        symbol = "[T]";
    }

    public Todo(String description, boolean isDone){
        super(description, isDone);
        symbol = "[T]";
    }
}
