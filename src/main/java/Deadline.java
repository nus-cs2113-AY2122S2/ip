import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Deadline extends Todo {
    private Date by;

    public Deadline(String description, String by) {
        super(description);
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try{
            Date date = formatter1.parse(by);
            this.by = date;
        } catch (Exception e){
        }
    }

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    public void setBy(Date by) {
        this.by = by;
    }

    public Date getBy() {
        return by;
    }

    public String getString() {
        String done = "0";
        if (isDone) {
            done = "1";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return ("D," + done + "," + description + "," + dateFormat.format(by));
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return (super.toString() + " (by: " + dateFormat.format(by) + ")");
    }

    public void printTask() {
        System.out.println("[D]" + this);
    }
}