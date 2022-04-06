/**
 * Subclass of Task
 * User input to add a new ToDo is:
 * > todo [description]
 */
public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
