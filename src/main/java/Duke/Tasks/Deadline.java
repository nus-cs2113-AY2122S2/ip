package Duke.Tasks;

public class Deadline extends Task{
    protected String by;

    public Deadline(String description,int status, String by) {
        super(description,status);
        this.by = by;
    }

    @Override
    public String toString () {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String saveTasks() {
        return "D " + super.saveTasks() + " | " + this.by + System.lineSeparator();
    }
}
