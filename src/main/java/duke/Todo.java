package duke;

public class Todo extends Task {
    public String taskKind;
    protected boolean isDone = false;
    public Todo(String description) {
        super(description);
        isDone = false;
        this.taskKind = "[T]";
    }

    @Override
    public String toString(){
        String indicator;
        if (this.isDone){
            indicator = "[X]";
        } else indicator = "[ ]";
        String message = "[T]" + indicator + description;
        return message;
    }
    public void setDone(boolean done) {
        isDone = done;
    }
    public boolean isDone() {
        return isDone;
    }
}