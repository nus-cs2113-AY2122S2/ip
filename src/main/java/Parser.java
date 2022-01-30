public class Parser {
    private String input;
    private boolean isAddingTask;
    private boolean isMarkingTask;
    private boolean isListingTasks;
    private boolean isExiting;

    Parser() {
        this.input = "";
        this.isAddingTask = false;
        this.isMarkingTask = false;
        this.isListingTasks = false;
        this.isExiting = false;
    }

    private void reset() {
        input = "";
        isAddingTask = false;
        isMarkingTask = false;
        isListingTasks = false;
        isExiting = false;
    }

    public void parseString(String input) {
        reset();
        this.input = input;
        String[] inputArr = input.split(" ");

        if (inputArr[0].equals("bye")) {
            isExiting = true;
        } else if (inputArr[0].equals("list")) {
            isListingTasks = true;
        } else if (inputArr[0].equals("mark") || inputArr[0].equals("unmark")) {
            isMarkingTask = true;
        } else {
            isAddingTask = true;
        }
    }

    public boolean isAddingTask() {
        return isAddingTask;
    }

    public boolean isMarkingTask() {
        return isMarkingTask;
    }

    public boolean isListingTasks() {
        return isListingTasks;
    }

    public boolean isExiting() {
        return isExiting;
    }

    public String getAddedTask() {
        return input;
    }

    public String[] getMarkedTask() {
        return input.split(" ");
    }
}
