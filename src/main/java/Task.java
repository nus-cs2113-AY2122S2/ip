/**
 * Records single task instruction and total number of tasks
 */

public class Task {
    protected String instruction;
    protected static int number;

    public Task(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return "( )";
    }
}
