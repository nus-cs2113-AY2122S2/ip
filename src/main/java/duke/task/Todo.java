package duke.task;

public class Todo extends Task {

    public Todo(String description){
        super(description);
    }
    public Todo(String description, boolean isTaskDone){
        super(description);
    }
    public String getDescription(){
        return "[T]" + super.getDescription();
    }

}
