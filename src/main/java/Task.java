public class Task {
    protected String descrition;
    protected boolean isDone;

    public Task(String descrition)
    {
        this.descrition=descrition;
        this.isDone=false;
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
