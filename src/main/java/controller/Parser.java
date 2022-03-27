package controller;

import command.*;
import exception.DukeException;
import exception.InvalidCommandException;
//import command.MarkCommand;


public class Parser {
    private static final String INDENT = "    ";
    private static final String LINE="-------------------------------------------";
    public static final String SPACE = " ";
    private static final int MAX_TASK_COUNT = 100;
    private static final String DIR = "data/task-file";
    private static final String FILE_SEPARATOR = " | ";

    /**
     * Scan the input.
     * Specify the action.
     * Return the corresponding Command.
     *
     * @param fullCommand command taken from user.
     * @throws DukeException if the input is invalid.
     * @return specific command.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String action = getAction(fullCommand);
        switch(action){
        case "list":
            return new ListCommand();
        case "mark":
            return new UpdateCommand("mark",fullCommand);
            //updateTask(Integer.parseInt(input.split(SPACE)[1])-1,true);  //refractor get index
        case "unmark":
            return new UpdateCommand("unmark",fullCommand);
            //updateTask(Integer.parseInt(input.split(SPACE)[1])-1,false);
        case "todo":

        case "deadline":

        case "event":
            return new AddCommand(action, fullCommand);
        case "delete":
            return new DeleteCommand(fullCommand);
            //deleteTask(Integer.parseInt(input.split(SPACE)[1])-1);
        case "bye":
            return new ExitCommand();
        case "find":
            return new FindCommand(fullCommand);
        default:
            throw new InvalidCommandException();
        }
    }

    private static String getAction(String input){
        String action = input.split(SPACE)[0].toLowerCase();
        return action;
    }

}
