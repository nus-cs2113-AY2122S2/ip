package tasks;

public class ToDo extends Task {

    /**
     * Initializes a todo task by given task name
     * @param name the name of the todo task
     */
    public ToDo(String name){
        super(name);
    }

    @Override
    public void setListName(){
        if(!isDone){
            this.listName = "[T]" + this.unmarkedStatus + this.taskName;
        }else{
            this.listName = "[T]" + this.markedStatus + this.taskName;
        }
    }
}
