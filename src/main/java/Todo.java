public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public char typeOfTask() {
        return 'T';             //T represents Task
    }
}
