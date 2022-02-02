public class Task {

    protected String description;

    public Task(String description) {
        this.description = description;
    }

    public String toString(boolean isDone) {
        String doneSymbol;
        if (isDone) {
            doneSymbol = "X";
        } else {
            doneSymbol = " ";
        }
        return "[" + doneSymbol + "] " + description;
    }
}