public class Event extends Deadline{
    private String start;

    public Event(String description, String by, String start) {
        super(description, by);
        this.start = start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void printTask(){
        System.out.println("[E][" + super.getStatusIcon() + "] " + super.description + " (start: " + this.start + ", end: " + super.getBy() + ")");
    }
}
