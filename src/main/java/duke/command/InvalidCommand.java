package duke.command;

import java.util.ArrayList;

import static duke.common.Strings.*;

/**
 * Represents an invalid command. Prints error message upon execution.
 */
public class InvalidCommand extends Command {
    public InvalidCommand(String commandWord, String commandFormat) {
        this(commandWord, commandFormat, false);
    }

    public InvalidCommand(String commandWord, String commandFormat, boolean canAcceptDate) {
        super();
        commandFeedback.add(String.format(MESSAGE_INCORRECT_COMMAND_FORMAT, commandWord));
        commandFeedback.add(String.format(MESSAGE_COMMAND_USAGE, commandFormat));
        if (canAcceptDate) {
            commandFeedback.add(MESSAGE_DATE_SUPPORT);
        }
    }

    public InvalidCommand(String commandWord, String commandFormat, String alternateCommandFormat) {
        super();
        commandFeedback.add(String.format(MESSAGE_INCORRECT_COMMAND_FORMAT, commandWord));
        commandFeedback.add(String.format(MESSAGE_COMMAND_USAGE_ALT, commandFormat, alternateCommandFormat));
    }

    public InvalidCommand() {
        commandFeedback.add(MESSAGE_UNKNOWN_COMMAND);
    }

    @Override
    public ArrayList<String> execute() {
        return commandFeedback;
    }
}
