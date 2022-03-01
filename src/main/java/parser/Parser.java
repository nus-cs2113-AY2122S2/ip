package parser;

import commands.*;
import common.DukeException;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        fullCommand = fullCommand.trim();
        String[] splitCommands = fullCommand.split(" ", 2);
        String description;
        String commandWord = splitCommands[0].trim();
        int idx;
        Command cmd;

        switch (commandWord) {
        case TodoCommand.COMMAND_WORD:
            description = splitCommands[1].trim();
            cmd = prepareTodo(description);
            break;
        case DeadlineCommand.COMMAND_WORD:
            description = splitCommands[1].trim();
            cmd = prepareDeadline(description);
            break;
        case EventCommand.COMMAND_WORD:
            description = splitCommands[1].trim();
            cmd = prepareEvent(description);
            break;
        case ListCommand.COMMAND_WORD:
            cmd = new ListCommand();
            break;
        case ByeCommand.COMMAND_WORD:
            cmd = new ByeCommand();
            break;
        case DeleteCommand.COMMAND_WORD:
            try {
                description = splitCommands[1].trim();
                idx = Integer.parseInt(description);
                cmd = new DeleteCommand(Integer.parseInt(description));
            } catch (NumberFormatException e) {
                throw new DukeException("Index is not an integer.");
            }
            break;
        case MarkCommand.COMMAND_WORD:
            try {
                description = splitCommands[1].trim();
                idx = Integer.parseInt(description);
                cmd = new MarkCommand(idx);
            } catch (NumberFormatException e) {
                throw new DukeException("Index is not an integer.");
            }
            break;
        case UnmarkCommand.COMMAND_WORD:
            try {
                description = splitCommands[1].trim();
                idx = Integer.parseInt(description);
                cmd = new UnmarkCommand(Integer.parseInt(description));
            } catch (NumberFormatException e) {
                throw new DukeException("Index is not an integer.");
            }
            break;
        case HelpCommand.COMMAND_WORD:
            cmd = new HelpCommand();
            break;
        default:
            throw new DukeException("Sorry! I cannot read this command :(\n\t "
                    + "Type \"help\" to view supported command.");

        }

        return cmd;
    }

    private static Command prepareTodo(String description) {
        return new TodoCommand(description);
    }

    private static Command prepareDeadline(String fullDescription) {
        String[] descriptions = fullDescription.split("/by", 2);
        String description = descriptions[0].trim();
        String by = descriptions[1].trim();
        return new DeadlineCommand(description, by);
    }

    private static Command prepareEvent(String fullDescription) {
        String[] descriptions = fullDescription.split("/at", 2);
        String description = descriptions[0].trim();
        String at = descriptions[1].trim();
        return new EventCommand(description, at);
    }
}
