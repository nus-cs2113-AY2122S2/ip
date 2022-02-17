package Duke.Tasks;

public class Event extends Task{
    protected String at;

    public Event(String description,int status, String at) {
        super(description, status);
        this.at = at;
    }

    @Override
    public String toString () {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    public String saveTasks() {
        return "E " + super.saveTasks() + " | " + this.at + System.lineSeparator();
    }

}
