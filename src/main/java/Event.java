/**
 * Deals with command "event", takes in instruction and the event date after "/at"
 */

public class Event extends Task{

    protected String at;

    public Event(String instruction, String at) {
        super(instruction);
        this.at = at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    /**\
     * Method that will be called by printEventMessage
     * @return returns a message to print
     */
    @Override
    public String toString() {
        return "  (E)" + super.toString() + this.instruction + "(at: " + at + ")";
    }
}
