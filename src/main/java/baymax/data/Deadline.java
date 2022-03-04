package baymax.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate ddl;

    public Deadline(String description, String ddl) {
        super(description);
        try{
            this.ddl = LocalDate.parse(ddl);
        } catch(Exception e){
            this.ddl = LocalDate.parse(ddl,DateTimeFormatter.ofPattern("MM dd yyyy"));
        }
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon() ;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+
                "( by: "+ ddl.format(DateTimeFormatter.ofPattern("MM dd yyyy")) + ")";
    }

    @Override
    public String saveInfo(){
        String sep = " / ";
        return "D" + super.saveInfo() + sep + this.ddl.format(DateTimeFormatter.ofPattern("MM dd yyyy")) ;
    }

    @Override
    public String toString(){
        return super.toString();
    }
}