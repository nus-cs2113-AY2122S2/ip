public class Task {
    private String name;
    private boolean marked = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public Task(String name, boolean marked) {
        this.name = name;
        this.marked = marked;
    }
    public String toString(){
        return " [ ][ ] " + getName();
    }
}
