/**
 * subclass of Task
 * To add a new Todo:
 * > todo [description]
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
