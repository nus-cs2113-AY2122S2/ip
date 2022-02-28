import commands.*;
import exceptions.InvalidCommandException;
import exceptions.MissingDescriptionException;

public class Parser {

    public static Command parseInput(String parsedInput) throws InvalidCommandException, MissingDescriptionException {
        String commandWord = parsedInput.split(" ")[0];

        switch (commandWord) {
        case "todo":
            return new TodoCommand(parsedInput);
        case "deadline":
            return new DeadlineCommand(parsedInput);
        case "event":
            return new EventCommand(parsedInput);
        case "list":
            return new ListCommand();
        case "delete":
            return new DeleteCommand(parsedInput);
        case "mark":
            return new MarkCommand(parsedInput);
        case "unmark":
            return new UnmarkCommand(parsedInput);
        default:
            throw new InvalidCommandException();
        }
    }
}
