public class Task {
    private String description;
    private boolean isDone;
    private String taskTypeSymbol;

    public Task(String description, String taskTypeSymbol){
        this.description = description;
        this.isDone = false;

        // Here we assign a String to taskTypeSymbol, which will be passed
        // by the constructor of the subclasses Event("E"), Deadline("D"), Todo("T")
        this.taskTypeSymbol = taskTypeSymbol;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone(){
        isDone = false;
    }

    //overriding method toString in Object class
    public String toString(){
        return "["+taskTypeSymbol+"][" + getStatusIcon() + "] " + description;
    }
}
