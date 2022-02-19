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

public class Parser {

    public Command parseCommand(String userInput) throws NonExistentCommandException, IllegalFormatException {
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
        case "list":
            return new ListCommand();
        case "exit":
            return new ExitCommand();
        default:
            throw new NonExistentCommandException();
        }
    }

    private Command prepareTodo(String args){
        Task todo = new Todo(args);
        return new AddCommand(todo);
    }

    private Command prepareEvent(String args) throws IllegalFormatException {
        String[] arrOfS = args.split("/");
        if(arrOfS.length < 2){
            throw new IllegalFormatException();
        }
        Task event = new Event(arrOfS[0], arrOfS[1]);
        return new AddCommand(event);
    }

    private Command prepareDDL(String args) throws IllegalFormatException {
        String[] arrOfS = args.split("/");
        if(arrOfS.length < 2){
            throw new IllegalFormatException();
        }
        Task ddl = new Deadline(arrOfS[0], arrOfS[1]);
        return new AddCommand(ddl);
    }

    private Command prepareMark(String args){
        int index;
        index = Integer.parseInt(args);
        return new MarkCommand(index);
    }

    private Command prepareUnmark(String args){
        int index;
        index = Integer.parseInt(args);
        return new UnmarkCommand(index);
    }

    private Command prepareDelete(String args){
        int index;
        index = Integer.parseInt(args);
        return new DeleteCommand(index);
    }
}
