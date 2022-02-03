public class Todo extends Task {

    private boolean isDone;

    public Todo(String description) {
        super(description);
        this.type = "T";

    }
    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    //need redo
    public String toString() {
        return super.toString();
    }
}
