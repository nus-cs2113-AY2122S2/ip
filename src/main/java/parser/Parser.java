package parser;

import commands.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ui.Ui;

/**
 * Represents a parser that processes the user's input string to identify commands and task attributes
 */
public class Parser {

    /**
     * Parses the user's input string and identifies the command issued by the user
     *
     * @param userInput User's input string
     * @return User's command
     * */
    public static Command parse(String userInput) {
        String[] splitUserInput = userInput.split(" ");
        String commandWord = splitUserInput[0];
        Command command = new Command();
        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            command = new ListCommand();
            break;
        case MarkCommand.COMMAND_WORD:
            command = new MarkCommand(userInput);
            break;
        case UnmarkCommand.COMMAND_WORD:
            command = new UnmarkCommand(userInput);
            break;
        case DeleteCommand.COMMAND_WORD:
            command = new DeleteCommand(userInput);
            break;
        case TodoCommand.COMMAND_WORD:
            command = new TodoCommand(userInput);
            break;
        case DeadlineCommand.COMMAND_WORD:
            command = new DeadlineCommand(userInput);
            break;
        case EventCommand.COMMAND_WORD:
            command = new EventCommand(userInput);
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        case EmptyCommand.COMMAND_WORD:
            command = new EmptyCommand();
            break;
        case FindCommand.COMMAND_WORD:
            command = new FindCommand(userInput);
            break;
        default:
            command = new WrongCommand();
            break;
        }
        return command;
    }

    public static LocalDateTime parseDateTime(String dateTimeString) {
        LocalDateTime dateTime = null; 
        try {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            dateTime = LocalDateTime.from(f.parse(dateTimeString));
        } catch (DateTimeException e) {
            System.out.println(Ui.INVALID_DATE_TIME_MESSAGE);
        }
        return dateTime; 
    }

}
