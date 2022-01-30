public class Task {
    public String unmarkedStatus = "[ ]";
    public String markedStatus = "[X]";
    public String taskName = "";
    public int status = 0;
    public String listName = "";

    public void setName(String name){
        this.taskName = name;
        setListName();
    }

    public String getTaskName(){
        return this.taskName;
    }

    public void setListName(){
        if(status == 0){
            this.listName = this.unmarkedStatus + this.taskName;
        }else{
            this.listName = this.markedStatus + this.taskName;
        }
    }

    public String getListName(){
        return this.listName;
    }

    public void mark(){
        this.status = 1;
        this.setListName();
    }

    public void unmark(){
        this.status = 0;
        this.setListName();
    }

    public void taskPrinter(){
        System.out.println(listName);
    }
}
