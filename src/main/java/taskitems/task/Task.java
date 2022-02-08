package taskitems.task;

public class Task {
    protected String name;
    protected boolean isMarked = false;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    @Override
    public String toString(){
        if (isMarked) {
            return "[U][X] " + name;
        } else {
            return "[U][ ] " + name;
        }
    }
}
