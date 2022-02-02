public class Event extends Task{
    public String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }


    @Override
    public String toString () {
        return "[E][" + this.getStatusIcon() +"] " + this.description + " (at: " + this.at + ")";
    }
}
