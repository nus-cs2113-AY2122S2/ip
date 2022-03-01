package parser;

import commands.*;
import common.DukeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses user input into a command for execution
     *
     * @param fullCommand the full user input command
     * @return the executable command corresponding to user input
     * @throws DukeException If the command format is incorrect
     */
    public static Command parse(String fullCommand) throws DukeException {
        fullCommand = fullCommand.trim();
        String[] splitCommands = fullCommand.split(" ", 2);
        String commandWord = splitCommands[0].trim();
        String description;
        int idx;
        Command cmd;

        try {
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
                description = splitCommands[1].trim();
                idx = Integer.parseInt(description);
                cmd = new DeleteCommand(idx);
                break;
            case MarkCommand.COMMAND_WORD:
                description = splitCommands[1].trim();
                idx = Integer.parseInt(description);
                cmd = new MarkCommand(idx);
                break;
            case UnmarkCommand.COMMAND_WORD:
                description = splitCommands[1].trim();
                idx = Integer.parseInt(description);
                cmd = new UnmarkCommand(idx);
                break;
            case HelpCommand.COMMAND_WORD:
                cmd = new HelpCommand();
                break;
            default:
                throw new DukeException("Sorry! I cannot read this command :(\n\t "
                        + "Type \"help\" to view supported command.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Index is not an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry! The command format is incorrect :(\n\t "
                    + "Type \"help\" to view supported command.");
        }

        return cmd;
    }

    private static Command prepareTodo(String description) throws DukeException {
        if(description.isEmpty()) {
            throw new DukeException("Todo description is unspecified.");
        }
        return new TodoCommand(description);
    }

    private static Command prepareDeadline(String fullDescription) throws DukeException {
        try {
            String[] descriptions = fullDescription.split("/by", 2);
            String description = descriptions[0].trim();
            String byStr = descriptions[1].trim();
            if(description.isEmpty() || byStr.isEmpty()) {
                throw new DukeException("Deadline description is unspecified.");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime by = LocalDateTime.parse(byStr, formatter);

            return new DeadlineCommand(description, by);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Deadline description is unspecified.");
        } catch (DateTimeParseException e) {
            throw new DukeException("Deadline date or time format is incorrect.");
        }
    }

    private static Command prepareEvent(String fullDescription) throws DukeException {
        try {
            String[] descriptions = fullDescription.split("/at", 2);
            String description = descriptions[0].trim();
            String atStr = descriptions[1].trim();
            if(description.isEmpty() || atStr.isEmpty()) {
                throw new DukeException("Event description is unspecified.");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime at = LocalDateTime.parse(atStr, formatter);

            return new EventCommand(description, at);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Event description is unspecified.");
        } catch (DateTimeParseException e) {
            throw new DukeException("Event date or time format is incorrect.");
        }
    }
}
