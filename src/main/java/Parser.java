public class Parser {
    private String command;
    private String description;
    private boolean isExiting;
    // split.(" ", limit)
    Parser() {
        this.command = "";
        this.description = "";
        this.isExiting = false;
    }

    private void reset() {
        command = "";
        description = "";
        isExiting = false;
    }

    public void parseString(String userInput) {
        reset();
        setParam(userInput);
    }

    private void setParam(String input) {
        int spaceIndex = input.trim().indexOf(" ");
        command = input.split(" ")[0].trim().toLowerCase();
        if (spaceIndex != -1) {
            description = input.substring(spaceIndex + 1).trim();
        }
        if (command.equals("bye")) {
            isExiting = true;
        }
    }

    public boolean isExiting() {
        return isExiting;
    }

    public String getCommand() {
        return command;
    }

    public String[] getTaskDescription() throws DukeException {
        String[] splitDescription = splitStringBySlash(description);
        if (splitDescription[0].isEmpty()) {
            throw new DukeException(Ui.missingDescription(command));
        }
        return splitDescription;
    }

    public int getTaskId() throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException(Ui.missingDescription(command));
        }
        try {
            return Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw new DukeException(Ui.taskIdInWrongFormat());
        }
    }

    private String[] splitStringBySlash(String input) {
        String[] splitInput = input.split(" ");
        String[] splitOutput = new String[3];
        String taskDescription = "";
        String op = "";
        String date = "";
        boolean hasSlash = false;

        for (int i = 0; i < splitInput.length; i++) {
            if (splitInput[i].equals("/by") || splitInput[i].equals("/at")) {
                op = splitInput[i];
                hasSlash = true;
                continue;
            }
            if (hasSlash) {
                date += splitInput[i] + " ";
            } else {
                taskDescription += splitInput[i] + " ";
            }
        }

        splitOutput[0] = taskDescription.trim();
        splitOutput[1] = op.trim();
        splitOutput[2] = date.trim();
        return splitOutput;
    }
}
