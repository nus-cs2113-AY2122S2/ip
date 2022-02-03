public class ToDo extends Task{

    public ToDo(String name){
        super(name);
    }

    @Override
    public void setListName(){
        if(isDone == false){
            this.listName = "[T]" + this.unmarkedStatus + this.taskName;
        }else{
            this.listName = "[T]" + this.markedStatus + this.taskName;
        }
    }
}
