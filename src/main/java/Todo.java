public class Todo extends Task{
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
}
