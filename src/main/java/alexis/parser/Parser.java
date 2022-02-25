package alexis.parser;

import alexis.commands.*;
import alexis.exceptions.MissingDeadlineTimingException;
import alexis.exceptions.MissingEventTimingException;

import static alexis.ui.Ui.*;

public class Parser {

    public static Command parse(String fullCommand) {

        String[] userInputArr = fullCommand.trim().split(" ");
        String commandWord = userInputArr[0];
        Command newCommand = new Command();

        try {
            switch (commandWord) {
            case "list":
                newCommand = new DisplayListCommand();
                break;
            case "todo":
                newCommand = new TodoCommand(fullCommand.trim().substring(5));
                break;
            case "deadline":
                newCommand = new DeadlineCommand(fullCommand.trim().substring(9));
                break;
            case "event":
                newCommand = new EventCommand(fullCommand.trim().substring(6));
                break;
            case "mark":
                newCommand = new MarkCommand(fullCommand.trim().substring(5));
                break;
            case "unmark":
                newCommand = new UnmarkCommand(fullCommand.trim().substring(7));
                break;
            case "delete":
                newCommand = new DeleteCommand(fullCommand.trim().substring(7));
                break;
            case "bye":
                newCommand = new ByeCommand();
                break;
            default:
                newCommand = new IncorrectCommand();
                break;
            }
        } catch (MissingDeadlineTimingException e) {
            exceptionMessage(DEADLINE_EXCEPTION_MESSAGE_TEXT);
        } catch (MissingEventTimingException e) {
            exceptionMessage(EVENT_EXCEPTION_MESSAGE_TEXT);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please input a description/task number after your command");
        }

        return newCommand;
    }

}