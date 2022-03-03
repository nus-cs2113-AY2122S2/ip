package parser;

import commands.*;
public class Parser {

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
        default:
            command = new WrongCommand();
            break;
        }
        return command;
    }

}
