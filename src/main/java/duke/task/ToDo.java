package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String trim, boolean equals) {
        super();
    }

    public String getTask() {
        return "[T]" + super.getTask();
    }
}
