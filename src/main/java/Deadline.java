/**
 * Deals with command "deadline" , takes in instruction and the due date after "/by", returns a message to print
 */

public class Deadline extends Task {

    protected String by;

    public Deadline(String instruction, String by) {
        super(instruction);
        this.by = by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    /**
     * Method that will be called by printDeadlineMessage
     * @return returns a message to print
     */
    @Override
    public String toString() {
        return "  (D)" + super.toString() + this.instruction + "(by: " + by + ")";
    }
}