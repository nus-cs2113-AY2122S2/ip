public class Todo extends Task {
    private boolean isDone;

    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    @Override
    public String setDone(boolean isDone) {
        this.isDone = isDone;
        String feedback;
        if (this.isDone) {
            feedback = String.format("Nice! I've marked this task as done:\n%s", toString());
        } else {
            feedback = String.format("Nice! I've marked this task as done:\n%s", toString());
        }
        return feedback;
    }

    @Override
    public String toString() {
        String marking = "[T]";
        if (isDone) {
            marking += "[X]";
        } else {
            marking += "[ ]";
        }
        return marking + " " + super.toString();
    }
}
