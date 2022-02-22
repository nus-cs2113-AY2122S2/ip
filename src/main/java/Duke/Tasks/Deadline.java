package Duke.Tasks;

public class Deadline extends Task {
    protected String date;

    public Deadline(String description,boolean isDone, String date) {
        super(description,isDone);
        this.date = date;
    }

    @Override
    public String toString () {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }

    @Override
    public String saveTasks() {
        return "D " + super.saveTasks() + " | " + this.date + System.lineSeparator();
    }
}
