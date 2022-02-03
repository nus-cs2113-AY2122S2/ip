public class Event extends Task{
    public String by;

    public Event(String name, String by){
        super(name);
        this.by = by;
        setListName();
    }

    @Override
    public void setListName(){
        if(isDone == false){
            this.listName = "[E]" + this.unmarkedStatus + this.taskName + "(at: " + by + ")";
        }else{
            this.listName = "[E]" + this.markedStatus + this.taskName + "(at: " + by + ")";
        }
    }
}
