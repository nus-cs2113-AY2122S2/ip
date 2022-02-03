public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        return "[T]" + (isDone ? "[X]"+ this.description : "[ ]"+ this.description); // mark done task with X
    }

    public void markTask() { 
        this.isDone = true; 
    }

    public void unmarkTask() { 
        this.isDone = false; 
    }

    
}