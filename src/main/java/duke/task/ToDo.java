package duke.task;

public class ToDo extends Task{

    public ToDo(String description, String typeOfTask) {
        super(description, typeOfTask);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
