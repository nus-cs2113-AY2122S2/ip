package taskitems.task;

public abstract class Task {
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

    public abstract String toString();

    public abstract String saveString();
}
