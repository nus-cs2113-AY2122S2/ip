public class Deadline extends Task{
    public String by;

    public Deadline(String name, String by){
        super(name);
        this.by = by;
    }

    @Override
    public String getListName(){
        return "[D]" + super.getListName() + "(by: " + by + ")";
    }
}
