package baymax.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate time;

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