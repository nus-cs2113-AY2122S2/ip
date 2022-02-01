public class Deadline extends Task{
    private String by;

    public String getTypeIcon() { return "D"; }

    public Deadline(String taskDescription, String deadline) {
        super(taskDescription);
        this.by = deadline;
    }

    public void printItem() {
        String message = "[" + getTypeIcon() + "]" + "[" + getStatusIcon() + "] " + getDescription();
        System.out.println(message);
    }

    public String getDeadlineDate() { return by; }

    public String getDescription() {
        String message = super.getDescription() + " (by: " + getDeadlineDate() + ")";
        return message;
    }
}
