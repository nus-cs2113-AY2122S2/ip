package boba.command;

/**
 * Enum for the valid Commands allowed for Boba chat-bot
 */
public enum Command {
    EXIT, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, HELP, NONE;

    /**
     * Determine what command user is using, and return the enum version.
     * If the command is not valid, the default version is returned.
     * @param input Input by the user given to the bot
     * @return The boba.command.Command that is being used
     */
    public static Command getCommand(String input) {
        // ternary operation for one word commands
        int index = input.indexOf(" ");
        String command = index == -1 ? input : input.substring(0, index) ;
        switch (command) {
        case ("bye"):
            return EXIT;
        case ("list"):
            return LIST;
        case ("todo"):
            return TODO;
        case ("deadline"):
            return DEADLINE;
        case ("event"):
            return EVENT;
        case ("mark"):
            return MARK;
        case ("unmark"):
            return UNMARK;
        case ("help"):
            return HELP;
        default:
            return NONE;
        }
    }
}
