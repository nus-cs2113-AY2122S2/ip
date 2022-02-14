package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String descrition)
    {
        this.description=descrition;
        this.isDone=false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }
    public void setDone(boolean isDone)
    {
        if (isDone){
            System.out.println("Nice work!I've marked this task as done:");
            this.isDone=true;
        }else {
            System.out.println("OK, I've marked this task as not done yet:");
            this.isDone=false;
        }
    }
    public String getTask(){
        return "["+this.getStatusIcon()+"]"+this.descrition;
    }
}
