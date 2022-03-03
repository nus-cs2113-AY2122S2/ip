package duke.util;

import duke.Commands.*;
import duke.Commands.Command;
import duke.exception.IllegalFormatException;
import duke.exception.IndexOutOfRangeException;
import duke.exception.NonExistentCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses input.
 */
public class Parser {

    /**
     * Parses user's input into Command.
     * @param userInput user input string
     * @return command
     * @throws NonExistentCommandException if the command does not exist.
     * @throws IllegalFormatException if the format of input is not correct.
     * @throws IndexOutOfBoundsException if the user enters too many parameters.
     */

    public Command parseCommand(String userInput) throws NonExistentCommandException, IllegalFormatException, IndexOutOfBoundsException {
        String[] arrOfS = userInput.split(" ",2);
        if (arrOfS.length<1){
            throw new IllegalFormatException();
        }
        String commandWord = arrOfS[0];

        switch (commandWord) {
        case "todo":
            String arguments = arrOfS[1];
            return prepareTodo(arguments);
        case "event":
            arguments = arrOfS[1];
            return prepareEvent(arguments);
        case "deadline":
            arguments = arrOfS[1];
            return prepareDDL(arguments);
        case "mark":
            arguments = arrOfS[1];
            return prepareMark(arguments);
        case "unmark":
            arguments = arrOfS[1];
            return prepareUnmark(arguments);
        case "delete":
            arguments = arrOfS[1];
            return prepareDelete(arguments);
        case "find":
            arguments = arrOfS[1];
            return new FindCommand(arguments);
        case "list":
            return new ListCommand();
        case "exit":
            return new ExitCommand();
        default:
            throw new NonExistentCommandException();
        }
    }

    /**
     * Parses arguments to create a todo object
     * @param args string
     * @return prepared command
     */

    private Command prepareTodo(String args){
        Task todo = new Todo(args);
        return new AddCommand(todo);
    }
    /**
     * Parses arguments to create an event object
     * @param args string
     * @return prepared command
     */

    private Command prepareEvent(String args) throws IllegalFormatException {
        String[] arrOfS = args.split("/");
        if(arrOfS.length < 2){
            throw new IllegalFormatException();
        }
        Task event = new Event(arrOfS[0], arrOfS[1]);
        return new AddCommand(event);
    }
    /**
     * Parses arguments to create a deadline object
     * @param args string
     * @return prepared command
     */
    private Command prepareDDL(String args) throws IllegalFormatException {
        String[] arrOfS = args.split("/");
        if(arrOfS.length < 2){
            throw new IllegalFormatException();
        }
        Task deadline = new Deadline(arrOfS[0], arrOfS[1]);
        return new AddCommand(deadline);
    }

    /**
     * Parses arguments for mark command
     * @param args string
     * @return prepared command
     */

    private Command prepareMark(String args){
        int index;
        index = Integer.parseInt(args);
        return new MarkCommand(index);
    }

    /**
     * Parses arguments for unmark command
     * @param args string
     * @return prepared command
     */
    private Command prepareUnmark(String args){
        int index;
        index = Integer.parseInt(args);
        return new UnmarkCommand(index);
    }

    /**
     * Parses arguments for delete command
     * @param args string
     * @return prepared command
     */

    private Command prepareDelete(String args){
        int index;
        index = Integer.parseInt(args);
        return new DeleteCommand(index);
    }


}
