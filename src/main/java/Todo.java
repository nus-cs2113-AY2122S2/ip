public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String toString(){
        return "[" + super.getStatusIcon() + "] " + super.description;
    }

    public void printTask() {
        System.out.println("[T]" + toString());
    }
}
