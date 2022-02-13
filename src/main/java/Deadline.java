public class Deadline extends Task{
    protected String by;
    protected String icon = "D";
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public String getIcon() {
        return icon;
    }

    public String getBy() {
        return by;
    }
    public String showDate(){return "(by: " + getBy() + ")" ;}
}
