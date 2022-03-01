public class Event extends Task{
    String at;

    public Event(String task, String at){
        super(task);
        this.at = at;
    }

    public String toString(){
        return ("[E][" + getStatusIcon() + "] " + getDescription() + " (at: " + getAt() +")");
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }
}
