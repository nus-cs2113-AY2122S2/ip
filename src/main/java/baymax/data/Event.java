package baymax.data;

public class Event extends Task {

    private String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String getDescription() {
        return super.getDescription()+ "( at: "+ time + ")";
    }

    @Override
    public String saveInfo(){
        String sep =" / ";
        return "E" + super.saveInfo() + sep + this.time;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString();
    }
}