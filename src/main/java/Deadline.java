public class Deadline extends Task{
    private String deadlineDate;

    public String getTypeIcon() {
        return "D";
    }

    public Deadline(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadlineDate = deadline;
    }

    public void printItem() {
        String message = "[" + getTypeIcon() + "]" + "[" + getStatusIcon() + "] " + getDescription();
        System.out.println(message);
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public String getDescription() {
        String message = super.getDescription() + " (by: " + getDeadlineDate() + ")";
        return message;
    }
}
