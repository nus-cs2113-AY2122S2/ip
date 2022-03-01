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

    @Override
    public String toString() {
        return "  (E)" + super.toString() + this.instruction + "(at: " + at + ")";
    }
}
