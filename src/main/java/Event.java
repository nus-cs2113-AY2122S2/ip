import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Deadline {
    private Date start;

    public Event(String description, Date by, Date start) {
        super(description, by);
        this.start = start;
    }
    public Event(String description, String by, String start) {
        super(description, by);
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try{
            Date date = formatter1.parse(start);
            this.start = date;
        } catch (Exception e){
        }
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return "[" + super.getStatusIcon() + "] " + super.description + " (start: " + dateFormat.format(this.start) + ", end: " + dateFormat.format(super.getBy()) + ")";
    }

    public String getString() {
        String done = "0";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        if (isDone) {
            done = "1";
        }
        return ("E," + done + "," + description + "," + dateFormat.format(start) + "," + dateFormat.format(super.getBy()));
    }

    public void printTask() {
        System.out.println("[E]" + this);
    }
}
