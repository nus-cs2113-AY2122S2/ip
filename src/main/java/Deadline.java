public class Deadline extends Task{
    protected String deadline;
    public Deadline(String name, boolean marked, String deadline){
        super(name, marked);
        this.deadline = deadline;
    }

    public String getDeadline(){
        return deadline;
    }

    public String toString(){
        if (super.getMarked()){
            return " [D][X] " + getName() + " (by: " + getDeadline() + ")";
        }else{
            return " [D][ ] " + getName() + " (by: " + getDeadline() + ")";
        }
    }
}
