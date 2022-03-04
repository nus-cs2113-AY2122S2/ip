package controller;

import command.*;
//import command.MarkCommand;


public class Parser {
    private static final String INDENT = "    ";
    private static final String LINE="-------------------------------------------";
    public static final String SPACE = " ";
    private static final int MAX_TASK_COUNT = 100;
    private static final String DIR = "data/task-file";
    private static final String FILE_SEPARATOR = " | ";
    public static Command parse(String fullCommand) throws DukeException {
        String action = getAction(fullCommand);
        switch(action){
        case "list":
            return new ListCommand();
        case "mark":
            return new UpdateCommand("mark",Integer.parseInt(fullCommand.split(SPACE)[1])-1);
            //updateTask(Integer.parseInt(input.split(SPACE)[1])-1,true);  //refractor get index
        case "unmark":
            return new UpdateCommand("unmark",Integer.parseInt(fullCommand.split(SPACE)[1])-1);
            //updateTask(Integer.parseInt(input.split(SPACE)[1])-1,false);
        case "todo":

        case "deadline":

        case "event":
            return new AddCommand(action, fullCommand);
        case "delete":
            return new DeleteCommand(Integer.parseInt(fullCommand.split(SPACE)[1])-1);
            //deleteTask(Integer.parseInt(input.split(SPACE)[1])-1);
        case "bye":
            return new ExitCommand();
        case "find":
            return new FindCommand(fullCommand);
        default:
            throw new DukeException();
        }
    }

    private static String getAction(String input){
        String action = input.split(SPACE)[0].toLowerCase();
        return action;
    }

}
