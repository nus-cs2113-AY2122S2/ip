public class Task {
    protected String instruction;
    protected String[] instructions;
    protected int number;
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

    /*public void printType(){
        if(isTodo) {
            System.out.print("T");
        } else if(isDeadline){
            System.out.print("D");
        } else if(isEvent){
            System.out.print("E");
        }
    }*/

    @Override
    public String toString() {
        return "[" + getStatusIcon() +"]";
    }
}
