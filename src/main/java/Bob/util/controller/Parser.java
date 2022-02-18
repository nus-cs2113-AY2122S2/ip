package bob.util.controller;

/**
 * A helper that handles the parsing of user inputs.
 */
public class Parser {
    /**
     * Takes in a command delimited by a space and parses it into 2 trimmed tokens.
     *
     * @param command The command to be parsed.
     * @return A list containing the main command then its details.
     */
    public static String[] parseCommand(String command, String delimiter) {
        String[] commandToken = command.split(delimiter, Command.TOKEN_LENGTH);
        commandToken[0] = commandToken[0].trim();
        if (commandToken.length == Command.TOKEN_LENGTH) {
            commandToken[1] = commandToken[1].trim();
            return commandToken;
        } else {
            return new String[]{commandToken[0], null};
        }
    }

    /**
     * Parses an integer string to its integer equivalent.
     *
     * @param text the integer to be formatted.
     * @return an integer formatted text.
     * @throws NumberFormatException if text is not an integer.
     */
    public static int stringToInt(String text) throws NumberFormatException {
        return Integer.parseInt(text);
    }
}
