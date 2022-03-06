package duke.task;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isTaskDone){
        this.description = description;
        this.isDone = isTaskDone;
    }

    public String getTask() {
        return showStatus() + this.description;
    }
    public String getDescription(){
        return showStatus() + description;
    }
    public void setDescription(String input){
        description = input;
    }
    public boolean getTaskStatus(){
        return isDone;
    }
    public void setMark(){
        this.isDone = true;
    }
    public void setUnmark(){
        this.isDone = false;
    }
    public String showStatus(){
        if(isDone==true){
            return("[X] ");
        }
        else{
            return("[ ] ");
        }
    }

}
