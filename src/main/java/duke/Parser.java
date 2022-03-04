package duke;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String commandWord = fullCommand.split(" ", 2)[0];
        int taskIndex;

        switch (commandWord) {
        case "list":
            return new listCommand();
        case "bye":
            return new exitCommand();
        case "todo":
        case "deadline":
        case "event":
            return new addCommand(commandWord, getArguments(fullCommand));
        case "mark":
            taskIndex = Integer.parseInt(getArguments(fullCommand)) - 1;
            return new markCommand(taskIndex);
        case "unmark":
            taskIndex = Integer.parseInt(getArguments(fullCommand)) - 1;
            return new unmarkCommand(taskIndex);
        case "delete":
            taskIndex = Integer.parseInt(getArguments(fullCommand)) - 1;
            return new deleteCommand(taskIndex);
        default:
            // Throw exception if unknown command inputted
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static String getArguments(String fullCommand) throws DukeException {
        if (fullCommand.split(" ", 2).length < 2) {
            throw new DukeException("OOPS!!! There are missing descriptions.");
        }

        return fullCommand.split(" ", 2)[1];
    }
}
