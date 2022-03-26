import commands.*;
import exceptions.InvalidCommandException;
import exceptions.MissingDescriptionException;

/**
 * This class handles the input and performs the correct instruction based on the input.
 */
public class Parser {
    /**
     * Collects the input from the user and checks whether it contains a valid command word
     * then returns the correct command type based on user input.
     *
     * @param parsedInput The full user input
     * @return A command object relevant to the command word in the user input
     * @throws InvalidCommandException
     * @throws MissingDescriptionException
     */
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
        case "find":
            return new FindCommand(parsedInput);
        default:
            throw new InvalidCommandException();
        }
    }
}
