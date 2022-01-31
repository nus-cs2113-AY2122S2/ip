public class Deadline extends Todo {
    private String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public void setBy(String by) {
        this.by = by;
    }
    public String getBy() {
        return by;
    }
    public String toString() {
        return super.toString() + System.lineSeparator() + "do by: " + by;
    }
    public void printTask(){
        System.out.println("[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + this.by + ")");
    }
}