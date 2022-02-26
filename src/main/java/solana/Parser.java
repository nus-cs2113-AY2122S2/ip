package solana;

import solana.command.*;

/**
 * Represents the input parser and deals with making sense of user commands.
 */
public class Parser {
    public static final int COMMAND_INDEX = 0;
    public static final int DESCRIPTION_INDEX = 1;

    public static final int SPLIT_LIMIT = 2;

    protected String inputCommand;

    public Parser(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    /**
     * Separates the user input into the command and description for easy reference.
     *
     * @return String array of user input.
     */
    public String[] parseInput() {
        String[] inputAsArray = inputCommand.split(" ", SPLIT_LIMIT);
        return inputAsArray;
    }

    /**
     * Checks if the user input is incomplete. If so, it throws an error message.
     *
     * @param parsedInput String array of user input.
     * @throws SolanaException If command is incomplete.
     */
    public void checkDescription(String[] parsedInput) throws SolanaException {
        if (parsedInput.length == 1) {
            switch (parsedInput[COMMAND_INDEX]) {
            case "help":
                // Fallthrough
            case "bye":
                // Fallthrough
            case "list":
                return;
            case "mark":
                // Fallthrough
            case "delete":
                throw new SolanaException("Input a task number!");
            case "todo":
                throw new SolanaException("Description of todo cannot be empty!");
            case "deadline":
                throw new SolanaException("Description of deadline cannot be empty!");
            case "event":
                throw new SolanaException("Description of event cannot be empty!");
            case "find":
                throw new SolanaException("Input a keyword!");
            default:
                throw new SolanaException("Invalid command! Type \"help\" to see the list of supported commands");
            }
        }
    }

    /**
     * Checks if the user input is valid before returning a Command object with respect to their input. The
     * command object can then be executed to perform the said command.
     *
     * @return Command object with respect to user's input.
     */
    public Command parseCommand() {
        String[] parsedInput = parseInput();

        try {
            checkDescription(parsedInput);
        } catch (SolanaException e){
            return new HelpCommand(false, true);
        }

        switch (parsedInput[COMMAND_INDEX]) {
        case "help":
            return new HelpCommand(true, false);
        case "bye":
            System.out.println("Goodbye!");
            System.exit(0);
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parsedInput[DESCRIPTION_INDEX]);
        case "unmark":
            return new UnmarkCommand(parsedInput[DESCRIPTION_INDEX]);
        case "delete":
            return new DeleteCommand(parsedInput[DESCRIPTION_INDEX]);
        case "find":
            return new FindCommand(parsedInput[DESCRIPTION_INDEX]);
        case "todo":
            return new TodoCommand(parsedInput[DESCRIPTION_INDEX], true, false);
        case "deadline":
            return new DeadlineCommand(parsedInput[DESCRIPTION_INDEX], true, false);
        case "event":
            return new EventCommand(parsedInput[DESCRIPTION_INDEX], true, false);
        default:
            return new HelpCommand(false, false);
        }
    }
}
