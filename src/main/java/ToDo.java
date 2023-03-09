public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if (getStatus().equals("done")) {
            return "[T][X] " + getDescription();
        } else {
            return "[T][ ] " + getDescription();
        }
    }
}
