package duke.task;
public class Task {
    private String name;
    private boolean isMarked = false;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public boolean getMarked(){
        return isMarked;
    }

    public void setMarked(boolean marked){
        this.isMarked = marked;
    }

    public Task(String name, boolean isMarked) {
        this.name = name;
        this.isMarked = isMarked;
    }
    public String toString() {
        return " [ ][ ] " + getName();
    }
    public String getTaskDetails() {
        return "task | " + (getMarked() ? 1:0) + " | " + getName() + "\n";
    }
}
