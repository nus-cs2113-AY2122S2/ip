public class Deadline extends Task{
    String by;

    public Deadline(String task, String by){
        super(task);
        this.by = by;
    }

    public String toString(){
        if(this.isDone()){
            return ("[D][X] " + this.getTask() + " (by: " + this.getBy() +")\n");
        }else{
            return ("[D][ ] " + this.getTask() + " (by: " + this.getBy() +")\n");
        }
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
}
