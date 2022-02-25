package tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(String name, String by){
        super(name);
        this.by = by;
        setListName();
    }

    @Override
    public void setListName(){
        if(!isDone){
            this.listName = "[D]" + this.unmarkedStatus + this.taskName + "(by: " + by + ")";
        }else{
            this.listName = "[D]" + this.markedStatus + this.taskName + "(by: " + by + ")";
        }
    }
}
