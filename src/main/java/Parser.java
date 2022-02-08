public class Parser {
    private String command;
    private String description;
    private boolean isExiting;

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

    public String[] getTaskDescription() {
        return splitStringBySlash();
    }

    public int getTaskId() throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException(Ui.emptyDescription(command));
        }
        try {
            return Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw new DukeException(Ui.taskIdInWrongFormat());
        }
    }

    private String[] splitStringBySlash() {
        String[] splitInput = description.split(" ");
        String[] splitOutput = new String[2];
        String taskDescription = "";
        String date = "";
        boolean hasSlash = false;

        for (int i = 0; i < splitInput.length; i++) {
            if (splitInput[i].equals("/by") || splitInput[i].equals("/at")) {
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
        splitOutput[1] = date.trim();
        return splitOutput;
    }
}
