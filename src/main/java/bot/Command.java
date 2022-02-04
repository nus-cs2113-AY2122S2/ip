package bot;

import java.util.HashMap;
import java.util.Map;

/**
 * Command class is concerned with parsing the input from the user.
 *
 * Note that this class is not in fact responsible for the bot's individual commands (list command, todo command).
 */
public final class Command {
    /**
     * The actual command the user inputted.
     */
    private final String command;

    /**
     * This description that came with this command.
     */
    private final String desc;

    /**
     * The extra arguments specified with the command.
     */
    private final Map<String, String> arguments;

    /**
     * Creates a Command object.
     *
     * @param command   The actual command inputted by the user.
     * @param desc      Description for the command.
     * @param artifacts Extra arguments specified with this command.
     */
    private Command(String command, String desc, Map<String, String> artifacts) {
        this.command = command;
        this.desc = desc;
        this.arguments = artifacts;
    }

    /**
     * Creates a new Command from a user input line.
     *
     * @param input The inputted line from the console.
     * @return New Command object with parsed information.
     */
    public static Command fromString(String input) {
        // Trim the input.
        input = input.trim();

        // We first split the command with a space. This will allow us to get the first word
        // in the input. The first word will be the command that the user wants.
        String[] parsedOne = input.split(" ", 2);
        String command = parsedOne[0].toLowerCase();
        // If the length of the first parsed command is not more or equal to 2, then
        // there is no description or extra arguments.
        if (parsedOne.length < 2) {
            return new Command(command, "", new HashMap<>());
        }

        // Else, there is either a description or extra arguments.
        // To find out, we first split by the "/" character.
        String[] parsedTwo = parsedOne[1].split("/");
        // The first item in the list will always be the description.
        String task = parsedTwo[0].trim();
        // Then we parse the rest of the extra arguments.
        Map<String, String> extras = new HashMap<>();
        for (int i = 1; i < parsedTwo.length; i++) {
            // The first word will be the argument type.
            String[] parsedThree = parsedTwo[i].split(" ", 2);
            extras.put(parsedThree[0].toLowerCase(), parsedThree.length == 2 ? parsedThree[1] : "");
        }
        return new Command(command, task, extras);
    }

    /**
     * Gets the command.
     *
     * @return Command string.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Gets the command description.
     *
     * @return Command description.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Get the specified argument for this argument.
     *
     * @param arg The argument key.
     * @return Argument value, or NULL if not present.
     */
    public String getArgument(String arg) {
        return this.arguments.get(arg);
    }

    /**
     * Useful for debugging purposes.
     *
     * @return String representation of a parsed command.
     */
    @Override
    public String toString() {
        return String.format("Command(" +
                        "command: %s, desc: %s, arguments: %s)",
                this.command, this.desc, this.arguments);
    }
}
