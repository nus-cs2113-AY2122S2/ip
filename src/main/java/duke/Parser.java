package duke;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String commandWord = fullCommand.split(" ", 2)[0];
        String arguments;

        switch (commandWord) {
        case "list":
            return new listCommand();
        case "bye":
            return new exitCommand();
        case "todo":
        case "deadline":
        case "event":
            arguments = fullCommand.split(" ", 2)[1];
            return new addCommand(commandWord, arguments);
        case "mark":
            arguments = fullCommand.split(" ", 2)[1];
            return new markCommand(Integer.parseInt(arguments));
        case "unmark":
            arguments = fullCommand.split(" ", 2)[1];
            return new unmarkCommand(Integer.parseInt(arguments));
        default:
            // Throw exception if unknown command inputted
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
