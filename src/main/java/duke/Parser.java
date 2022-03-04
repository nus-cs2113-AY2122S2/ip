package duke;

/**
 * Represents a string parser to parse the user inputs.
 */
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

    /**
     * Parses user input.
     * Gets the command and description from the input.
     * Checks if the user intends to exit the program.
     *
     * @param input User input.
     */
    public void parseString(String input) {
        reset();
        command = getCommandFromInput(input);
        description = getDescriptionFromInput(input);
        if (command.equals("bye")) {
            isExiting = true;
        }
    }

    /**
     * Checks if the user intends to exit the program.
     * @return Boolean representing whether the user intends to exit the program.
     */
    public boolean isExiting() {
        return isExiting;
    }

    /**
     * Returns the command.
     *
     * @return String of the command.
     * @throws DukeException if the command is empty.
     */
    public String getCommand() throws DukeException {
        if (command.isEmpty()) {
            throw new DukeException(Ui.emptyInputError());
        }
        return command;
    }

    /**
     * Returns the command description.
     *
     * @return String of the command description.
     * @throws DukeException if the command description is empty.
     */
    public String getDescription() throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException(Ui.missingDescriptionError(command));
        }
        return description;
    }

    /**
     * Returns the task details in an array of strings.
     * [0] Task Description.
     * [1] Operation.
     * [2] Date/Time.
     *
     * @return Array of string that contains the task details.
     * @throws DukeException if the task description is empty.
     */
    public String[] getTaskDescription() throws DukeException {
        String[] splitDescription = splitString(description);
        if (splitDescription[0].isEmpty()) {
            throw new DukeException(Ui.missingDescriptionError(command));
        }
        return splitDescription;
    }

    /**
     * Returns the task ID.
     *
     * @return Integer representing the task ID.
     * @throws DukeException if the task ID is empty or in the wrong format.
     */
    public int getTaskId() throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException(Ui.missingDescriptionError(command));
        }
        try {
            return Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw new DukeException(Ui.wrongTaskIdFormatError());
        }
    }

    /**
     * Gets command from the user input.
     *
     * @param input User input.
     * @return String of the command.
     */
    private String getCommandFromInput(String input) {
        return input.split(" ")[0].trim().toLowerCase();
    }

    /**
     * Gets command description from the user input.
     *
     * @param input User input.
     * @return String of the command description.
     */
    private String getDescriptionFromInput(String input) {
        String str = "";
        int spaceIndex = input.trim().indexOf(" ");
        if (spaceIndex != -1) {
            str = input.substring(spaceIndex + 1).trim();
        }
        return str;
    }

    /**
     * Gets the task details from the command description.
     *
     * @param input Command description.
     * @return Array of string that contains the task details.
     */
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
