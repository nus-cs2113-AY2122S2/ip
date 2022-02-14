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

    public String getString() {
        String done = "0";
        if (isDone) {
            done = "1";
        }
        return ("D," + done + "," + description + "," + by);
    }

    public String toString() {
        return (super.toString() + " (by: " + by + ")");
    }

    public void printTask() {
        System.out.println("[D]" + this);
    }
}