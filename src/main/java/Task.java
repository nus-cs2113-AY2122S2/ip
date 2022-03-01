public class Task {
    protected String instruction;
    protected static int number;
    protected boolean isDone;

    public Task(String instruction) {
        this.instruction = instruction;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void setStatusIcon(Boolean isDone){
        this.isDone = isDone;
    }
    public void printStatus() {
        System.out.print(this);
    }

    @Override
    public String toString() {
        return "(" + getStatusIcon() +")";
    }
}
