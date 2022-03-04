package duke;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        // Do Something
        final String commandWord = fullCommand.split(" ", 2)[0];

        switch (commandWord) {
        case "list":
            return new listCommand();
        case "bye":
            return new exitCommand();
        case "todo":
            String arguments = fullCommand.split(" ", 2)[1];
            return new addCommand(commandWord, arguments);
        default:
            // Throw exception if unknown command inputted
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
