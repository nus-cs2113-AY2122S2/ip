package taskitems.task;

public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString(){
        if (isMarked) {
            return "[T][X] " + name;
        } else {
            return "[T][ ] " + name;
        }
    }

    @Override
    public String saveString() {
        if (isMarked) {
            return ("T," + name + ",1");
        } else {
            return ("T," + name + ",0");
        }
    }

}
