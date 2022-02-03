public class Event extends Task{
    String at;

    public Event(String task, String at){
        super(task);
        this.at = at;
    }

    public String toString(){
        if(this.isDone()){
            return ("[E][X] " + this.getTask() + " (at: " + this.getAt() +")\n");
        }else{
            return ("[E][ ] " + this.getTask() + " (at: " + this.getAt() +")\n");
        }
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }
}
