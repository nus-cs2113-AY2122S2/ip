package boba.parser;

import boba.command.Command;
import boba.command.DeadlineCommand;
import boba.command.DeleteCommand;
import boba.command.EventCommand;
import boba.command.ExitCommand;
import boba.command.HelpCommand;
import boba.command.ListCommand;
import boba.command.MarkCommand;
import boba.command.NoneCommand;
import boba.command.TodoCommand;
import boba.command.UnmarkCommand;
import boba.exception.BobaException;

public class Parser {

    /** Similar to String[] args,
     * this limit is imposed on how many arguments follow the initial command
     */
    private static final int SECTION_LIMIT = 2;

    /**
     * Determine what command user is using, and return the enum version.
     * If the command is not valid, the default version is returned.
     * @param input Input by the user given to the bot
     * @return The Command that is being used
     */
    public static Command parse(String input) throws BobaException{
        // ternary operation for one word commands
        int index = input.indexOf(" ");
        String command = index == -1 ? input : input.substring(0, index) ;
        try {
            String[] arguments = getArguments(command, input);
            switch (command) {
            case ("bye"):
                return new ExitCommand();
            case ("list"):
                return new ListCommand();
            case ("todo"):
                return new TodoCommand(arguments[0]);
            case ("deadline"):
                return new DeadlineCommand(arguments[0], arguments[1]);
            case ("event"):
                return new EventCommand(arguments[0], arguments[1]);
            case ("mark"):
                return new MarkCommand(arguments[0]);
            case ("unmark"):
                return new UnmarkCommand(arguments[0]);
            case ("delete"):
                return new DeleteCommand(arguments[0]);
            case ("help"):
                return new HelpCommand();
            default:
                return new NoneCommand();
            }
        } catch (BobaException e) {
            throw e;
        }
    }

    /**
     * Parses the remaining input for the arguments following the command.
     * Number of arguments is determined by what command.
     * @param input Input by the user given to the bot
     * @return The arguments
     */
    private static String[] getArguments(String command, String input) throws BobaException{
        String[] arguments = new String[SECTION_LIMIT];
        switch (command) {
        case ("deadline"):
        case ("event"):
            // both DEADLINE and EVENT have two arguments
            // both follow the same convention of using a slash command
            int slashIndex = input.indexOf("/");
            if (slashIndex == -1) {
                throw new BobaException("Remember to include the time using slash command");
            }
            arguments[1] = input.substring(input.indexOf(" ", slashIndex) + 1);
            input = input.substring(0, slashIndex - 1);
        case ("todo"):
        case ("mark"):
        case ("unmark"):
        case ("delete"):
            int spaceIndex = input.indexOf(" ");
            if (spaceIndex == -1) {
                throw new BobaException("Remember to include your argument");
            }
            arguments[0] = input.substring(spaceIndex + 1);
        default:
            // do nothing. default has no arguments, only the command
        }
        return arguments;
    }

}
