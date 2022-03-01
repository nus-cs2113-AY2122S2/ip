package tasks;

public class Task {
    protected String unmarkedStatus = "[ ]";
    protected String markedStatus = "[X]";
    protected String taskName; //the name of the task
    protected boolean isDone = false;
    protected String listName; //the name of the task that appears when listing

    public Task(String name){
        this.taskName = name;
        this.isDone = false;
        setListName();
    }

    public String getTaskName(){
        return this.taskName;
    }

    public void setListName(){
        if(!isDone){
            this.listName = this.unmarkedStatus + this.taskName;
        }else{
            this.listName = this.markedStatus + this.taskName;
        }
    }

    public String getListName(){
        return this.listName;
    }

    public void mark(){
        this.isDone = true;
        this.setListName();
    }

    public void unmark(){
        this.isDone = false;
        this.setListName();
    }

    public void taskPrinter(){
        System.out.println(listName);
    }
}
