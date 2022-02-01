public class Task {
    protected String description;
    protected boolean isMarked;
    protected int id;

    public Task(String description, int id) {
        this.description = description;
        this.id = id;
        this.isMarked = false;
    }

    public Task() {
        this("This task has not be defined.", -1);
    }

    public String getStatusIcon() {
        return (isMarked ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsDone() {
        return isMarked;
    }

    public void setIsMarked() {
        isMarked = true;
    }

    public void unsetIsMarked() {
        isMarked = false;
    }

    @Override
    public String toString() {
        return  "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}