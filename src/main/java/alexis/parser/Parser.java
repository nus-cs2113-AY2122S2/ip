package alexis.parser;

import alexis.commands.*;
import alexis.exceptions.MissingDeadlineTimingException;
import alexis.exceptions.MissingEventTimingException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static alexis.ui.Ui.*;

public class Parser {

    public static final String DATE_TIME_EXCEPTION_MESSAGE = "Please key in the date in the format dd/MM/yyyy";
    public static final String MISSING_DESCRIPTION_MESSAGE = "Please input a description/task number after your command";

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
            case "show":
                newCommand = new ShowCommand(fullCommand.trim().substring(5));
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
        } catch (DateTimeException e) {
            System.out.println(DATE_TIME_EXCEPTION_MESSAGE);
        } catch (MissingEventTimingException e) {
            exceptionMessage(EVENT_EXCEPTION_MESSAGE_TEXT);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(MISSING_DESCRIPTION_MESSAGE);
        }

        return newCommand;
    }

    public static LocalDate parseDate(String inputDate) {
        //following code finds the number of "/"s in inputDate
        int numOccurrences = 0;
        Matcher m = Pattern.compile("/", Pattern.LITERAL).matcher(inputDate);
        while (m.find()) {
            numOccurrences++;
        }

        if (numOccurrences != 2) {
            throw new DateTimeException(DATE_TIME_EXCEPTION_MESSAGE);
        } else {
            String[] unparsedDate = inputDate.split("/");
            return LocalDate.parse(unparsedDate[2] + "-" + unparsedDate[1] + "-" + unparsedDate[0]);
        }
    }

    public static String parseTiming(LocalDate date) {
        if (date.equals(null)) {
            throw new DateTimeException(DATE_TIME_EXCEPTION_MESSAGE);
        }

        String month = String.valueOf(date.getMonth()).substring(0, 3);
        String firstLetter = month.substring(0, 1);
        String secondAndThirdLetters = month.substring(1).toLowerCase();
        month = firstLetter + secondAndThirdLetters;
        String day = String.valueOf(date.getDayOfMonth());
        String year = String.valueOf(date.getYear());
        return month + " " + day + " " + year;

    }

}