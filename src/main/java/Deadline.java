public class Deadline extends Task {
    private String dueDate;

    public Deadline(String description,String dueDate){
        super(description,"D");
        this.dueDate=dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    // overriding method toString in Object class
    public String toString(){
        return super.toString()+" (by: "+dueDate+")";
    }
}
