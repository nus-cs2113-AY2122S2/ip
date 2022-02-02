public class Event extends Task{
    public String by;

    public Event(String name, String by){
        super(name);
        this.by = by;
    }

    @Override
    public String getListName(){
        return "[E]" + super.getListName() + "(at: " + by + ")";
    }
}
