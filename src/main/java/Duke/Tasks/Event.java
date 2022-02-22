package Duke.Tasks;

public class Event extends Task {
    protected String date;

    public Event(String description,boolean isDone, String date) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String toString () {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }

    @Override
    public String saveTasks() {
        return "E " + super.saveTasks() + " | " + this.date + System.lineSeparator();
    }

}
