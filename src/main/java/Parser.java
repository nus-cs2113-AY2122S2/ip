public class Parser {
    private String input;
    private String errorMessage;
    private boolean isValidInput;
    private boolean isAddingTask;
    private boolean isMarkingTask;
    private boolean isListingTasks;
    private boolean isExiting;

    Parser() {
        this.input = "";
        this.errorMessage = "";
        this.isValidInput = true;
        this.isAddingTask = false;
        this.isMarkingTask = false;
        this.isListingTasks = false;
        this.isExiting = false;
    }

    private void reset() {
        input = "";
        isValidInput = true;
        errorMessage = "";
        isAddingTask = false;
        isMarkingTask = false;
        isListingTasks = false;
        isExiting = false;
    }

    public void parseString(String input) {
        reset();
        this.input = input;
        String command = getCommand();
        setParam(command);
        if (isValidInput) {
            setInvalidCommand();
        } else {
            checkInputIsValid(command);
        }
    }

    private void setParam(String command) {
        if (command.equals("bye")) {
            isExiting = true;
        } else if (command.equals("list")) {
            isListingTasks = true;
        } else if (command.equals("mark") || command.equals("unmark")) {
            isMarkingTask = true;
        } else if (command.equals("todo") || command.equals("event")
                || command.equals("deadline")) {
            isAddingTask = true;
        } else {
            isValidInput = false;
        }
    }

    private void setInvalidCommand() {
        errorMessage = "Invalid Command";
    }

    private void checkInputIsValid(String command) {
        if (command.equals("bye")) {
            //
        } else if (command.equals("list")) {
            //
        } else if (command.equals("mark") || command.equals("unmark")) {
            //
        } else if (command.equals("todo") || command.equals("event")
                    || command.equals("deadline")) {
            //
        }
    }

    public boolean isValidInput() {
        return isValidInput;
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

    public String getCommand() {
        return input.split(" ")[0].trim().toLowerCase();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String[] getAddedTask() {
        return splitStringBySlash();
    }

    public String[] getMarkedTask() {
        return input.split(" ");
    }

    private String[] splitStringBySlash() {
        String[] splitInput = input.split(" ");
        String[] splitOutput = new String[2];
        String description = "";
        String date = "";
        boolean hasSlash = false;

        for (int i = 1; i < splitInput.length; i++) {
            if (splitInput[i].equals("/by") || splitInput[i].equals("/at")) {
                hasSlash = true;
                continue;
            }
            if (hasSlash) {
                date += splitInput[i] + " ";
            } else {
                description += splitInput[i] + " ";
            }
        }

        splitOutput[0] = description.trim();
        splitOutput[1] = date.trim();
        return splitOutput;
    }
}
