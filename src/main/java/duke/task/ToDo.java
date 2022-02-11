package duke.task;

public class ToDo extends Task{
    private static final String TODO_LOGO = "[T]";

    public ToDo(String description, String typeOfTask) {
        super(description, typeOfTask);
    }

    @Override
    public String toString() {
        return TODO_LOGO + super.toString();
    }
}
