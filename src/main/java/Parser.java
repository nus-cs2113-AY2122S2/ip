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

    private String getCommand(String input) {
        return input.split(" ")[0];
    }

    public void parseString(String input) {
        reset();
        String command = getCommand(input);

        if (command.equals("bye")) {
            isExiting = true;
        } else if (command.equals("list")) {
            isListingTasks = true;
        } else if (command.equals("mark") || command.equals("unmark")) {
            isMarkingTask = true;
            this.input = input;
        } else {
            isAddingTask = true;
            this.input = input;
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
