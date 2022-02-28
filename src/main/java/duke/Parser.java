package duke;

public class Parser {
    private String command;
    private String description;
    private boolean isExiting;

    public Parser() {
        this.command = "";
        this.description = "";
        this.isExiting = false;
    }

    private void reset() {
        command = "";
        description = "";
        isExiting = false;
    }

    public void parseString(String input) {
        reset();
        command = getCommandFromInput(input);
        description = getDescriptionFromInput(input);
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
        String[] splitDescription = splitString(description);
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
            throw new DukeException(Ui.wrongTaskIdFormat());
        }
    }

    private String getCommandFromInput(String input) {
        return input.split(" ")[0].trim().toLowerCase();
    }

    private String getDescriptionFromInput(String input) {
        String str = "";
        int spaceIndex = input.trim().indexOf(" ");
        if (spaceIndex != -1) {
            str = input.substring(spaceIndex + 1).trim();
        }
        return str;
    }

    private String[] splitString(String input) {
        String[] splitInput = input.split(" ");
        String taskDescription = "";
        String op = "";
        String date = "";
        boolean hasSlash = false;

        for (String str : splitInput) {
            if (str.equals("/by") || str.equals("/at")) {
                op = str;
                hasSlash = true;
                continue;
            }
            if (hasSlash) {
                date += str + " ";
            } else {
                taskDescription += str + " ";
            }
        }

        String[] splitOutput = new String[3];
        splitOutput[0] = taskDescription.trim();
        splitOutput[1] = op.trim();
        splitOutput[2] = date.trim();
        return splitOutput;
    }
}
