package jarvis.commands;

public class Deadline extends Task{
    private String deadlineDate;

    /**
     * Override getter function to retrieve Deadline's icon
     * @return An icon in String data type to represent a Deadline
     */
    public String getTypeIcon() {
        return "D";
    }

    public Deadline(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadlineDate = deadline;
    }

    /**
     * Override function to print a Deadline formatted with icon, status and description.
     */
    public void printItem() {
        String message = "[" + getTypeIcon() + "]" + "[" + getStatusIcon() + "] " + getDescription();
        System.out.println(message);
    }

    /**
     * Override Getter function to retrieve Deadline's date
     * @return A String to specify the Deadline's date
     */
    public String getDeadlineDate() {
        return deadlineDate;
    }

    /**
     * Override getter function to retrieve Deadline's description
     *
     * @return A String of Deadline's description
     */
    public String getDescription() {
        String message = super.getDescription() + " (by: " + getDeadlineDate() + ")";
        return message;
    }

    /**
     * Override function to export Deadline's data to be saved in a data file for when Jarvis is shutting down.
     *
     * @return String of formatted data of Deadline with icon, status, description and date.
     */
    public String exportData() {
        String status = isDone ? "YES" : "NO";
        return getTypeIcon() + " " + status + " " + super.getDescription() + " | " + this.getDeadlineDate();
    }
}
