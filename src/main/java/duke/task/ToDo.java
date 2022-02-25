package duke.task;

public class ToDo extends Task {
     
    public ToDo(String description) {
        super(description);
    }

    public String getType() {
        return "T";
    }

    public String getDate() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}