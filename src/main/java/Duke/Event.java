package Duke;
public class Event extends Task{
    protected String at;
    protected String icon = "E";
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    public String getIcon() {
        return icon;
    }

    public String getAt() {
        return at;
    }
    public String showDate(){return "(at: " + getAt() + ")" ;}
}
