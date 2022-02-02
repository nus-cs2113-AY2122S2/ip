public class Task {
    public String unmarkedStatus = "[ ]";
    public String markedStatus = "[X]";
    public String taskName = "";
    public boolean isDone = false;
    public String listName = "";

    public Task(String name){
        this.taskName = name;
        setListName();
    }

    public String getTaskName(){
        return this.taskName;
    }

    public void setListName(){
        if(isDone == false){
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
