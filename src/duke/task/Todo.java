package duke.task;
public class Todo extends Task {
    public Todo(String name, boolean marked){
        super(name,marked);
    }

    public String toString(){
        if (super.getMarked()){
            return " [T][X] " + getName();
        }else{
            return " [T][ ] " + getName();
        }
    }

    public String getTaskDetails(){
        return "todo | " + (getMarked() ? 1:0) + " | " + getName() + "\n";
    }
}
