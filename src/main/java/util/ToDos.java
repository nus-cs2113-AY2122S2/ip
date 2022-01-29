package util;

public class ToDos extends Task {
    /**
     * Class constructor
     *
     * @param description the Task description.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Overrides the toString function with formatted details of the deadline.
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
