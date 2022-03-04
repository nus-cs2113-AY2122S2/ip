package baymax.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Inherit from Task class,
 * with additioanl attribute: time storing the date of event
 */
public class Event extends Task {

    private LocalDate time;

    /**
     * class constructor
     * @param description description of the deadline task
     * @param time time of the task in format: MM dd yyyy
     */
    public Event(String description, String time) {
        super(description);
        try{
            this.time = LocalDate.parse(time);
        } catch(Exception e){
            this.time= LocalDate.parse(time,DateTimeFormatter.ofPattern("MM dd yyyy"));
        }
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return super.getDescription()+
                "( at: "+ time.format(DateTimeFormatter.ofPattern("MM dd yyyy")) + ")";
    }

    @Override
    public String saveInfo(){
        String sep =" / ";
        return "E" + super.saveInfo() + sep + this.time.format(DateTimeFormatter.ofPattern("MM dd yyyy")) ;
    }

    @Override
    public String toString(){
        return super.toString();
    }
}