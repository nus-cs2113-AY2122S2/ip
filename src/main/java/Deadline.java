public class Deadline extends Task{
    public String by;

    public Deadline(String name, String by){
        super(name);
        this.by = by;
        setListName();
    }

    @Override
    public void setListName(){
        if(isDone == false){
            this.listName = "[D]" + this.unmarkedStatus + this.taskName + "(by: " + by + ")";
        }else{
            this.listName = "[D]" + this.markedStatus + this.taskName + "(by: " + by + ")";
        }
    }
}
