public class Todo extends Task{
    public Todo(String description) {
        super(description);

    }
    public void printTodo() {
        System.out.println("[" + this.getStatusIcon() + "] " + this.getDescription());
    }
    public void printTodo(Integer i) {
        System.out.println((i + 1) + ". [" + this.getStatusIcon() + "] " + this.getDescription());
    }

    public String toString() {
        return "[T] " + super.toString() + "\n";
    }
}
