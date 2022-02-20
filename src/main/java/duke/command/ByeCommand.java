package duke.command;

import java.util.ArrayList;

import static duke.common.Strings.MESSAGE_GOODBYE;

/**
 * Requests program termination.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super();
        commandFeedback.add(MESSAGE_GOODBYE);
        commandFeedback.add(null);
    }

    @Override
    public ArrayList<String> execute() {
        return commandFeedback;
    }
}
