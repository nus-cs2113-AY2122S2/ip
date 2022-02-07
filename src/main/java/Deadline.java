public class Deadline extends Task{
    String by;

    public Deadline(String task, String by){
        super(task);
        this.by = by;
    }

    @Override
    public String toString(){
        return ("[D][" + getStatusIcon() + "] " + getDescription() + " (by: " + getBy() +")\n");
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
}
