public class ToDo extends Task {
    public ToDo(String content) {
        super(content);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
