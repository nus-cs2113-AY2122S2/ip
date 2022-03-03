package main.java.duke.parser;

import main.java.duke.Duke;
import main.java.duke.ui.Ui;
import main.java.duke.exception.DukeException;
import main.java.duke.command.Command;
import main.java.duke.command.ListCommand;
import main.java.duke.command.UnmarkCommand;
import main.java.duke.command.MarkCommand;
import main.java.duke.command.DeleteCommand;
import main.java.duke.command.ToDoCommand;
import main.java.duke.command.DeadlineCommand;
import main.java.duke.command.EventCommand;
import main.java.duke.command.InvalidCommand;
import main.java.duke.command.commandsCommand;
import main.java.duke.command.CheckDateCommand;
import main.java.duke.command.ByeCommand;

public class Parser {

    private final String LIST = "list";
    private final String UNMARK = "unmark";
    private final String MARK = "mark";
    private final String TODO = "todo";
    private final String DEADLINE = "deadline";
    private final String EVENT = "event";
    private final String COMMANDS = "commands";
    private final String DELETE = "delete";
    private final String CHECK_DATE = "check date";
    private final String BYE = "bye";
    
    public Command parse(String input) throws DukeException {
        String lowerCaseInput = input.toLowerCase();
        if (lowerCaseInput.startsWith(LIST)) {
            return parseList();

        } else if (lowerCaseInput.startsWith(UNMARK)) {
            return parseUnmark(input);

        } else if (lowerCaseInput.startsWith(MARK)) {
            return parseMark(input);

        } else if (lowerCaseInput.startsWith(TODO)) {
            return parseToDo(input);

        } else if (lowerCaseInput.startsWith(DEADLINE)) {
            if (input.contains("/by")) {
                return parseDeadline(input);
            } else {
                throw new DukeException("Oh no! You need to include a '/by' in the command!");
            }

        } else if (lowerCaseInput.startsWith(EVENT)) {
            if (input.contains("/at")) {
                return parseEvent(input);
            } else {
                throw new DukeException("Oh no! You need to include a '/at' in the command!");
            }

        } else if (lowerCaseInput.startsWith(COMMANDS)) {
            return parseCommands();

        } else if (lowerCaseInput.startsWith(DELETE)) {
            return parseDelete(input);

        } else if (lowerCaseInput.startsWith(CHECK_DATE)) {
            return parseCheckDate(input);

        } else if (lowerCaseInput.startsWith(BYE)) {
            return new ByeCommand();

        } else {
            return new InvalidCommand();

        }
    }

    private boolean isNum(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        } 
        return true;
    }

    private Command parseList() {
        return new ListCommand();
    }

    private Command parseUnmark(String input) throws DukeException {
        String[] splitString = input.split(" ", 2);
        if (splitString.length < 2) {
            throw new DukeException("Oh no! You need to choose a task to unmark!");

        } else if (isNum(splitString[1])) {
            int markInt = Integer.parseInt(splitString[1]);
            if ((markInt > Duke.taskCounter) || (markInt < 1)) {
                throw new DukeException("Oh no! The number you have chosen is not valid!");

            } else if (!Duke.tasks.get(markInt - 1).isDone()) {
                throw new DukeException("Oh no! The task you have selected is not been marked!");

            }
            else {
                return new UnmarkCommand(markInt);

            }
        } else {
            throw new DukeException("Oh no! You need to choose a number!");

        } 
    }

    private Command parseMark(String input) throws DukeException {
        String[] splitString = input.split(" ", 2);
        if (splitString.length < 2) {
            throw new DukeException("Oh no! You need to choose a task to unmark!");

        } else if (isNum(splitString[1])) {
            int markInt = Integer.parseInt(splitString[1]);
            if ((markInt > Duke.taskCounter) || (markInt < 1)) {
                throw new DukeException("Oh no! The number you have chosen is not valid!");

            } else if (Duke.tasks.get(markInt - 1).isDone()) {
                throw new DukeException("Oh no! The task you have selected is already been marked");

            }
            else {
                return new MarkCommand(markInt);

            }
        } else {
            throw new DukeException("Oh no! You need to choose a number!");

        } 
    }

    private Command parseDelete(String input) throws DukeException {
        String[] splitString = input.split(" ", 2);
        if (splitString.length < 2) {
            throw new DukeException("Oh no! You need to choose a task to unmark!");

        } else if (isNum(splitString[1])) {
            int deleteInt = Integer.parseInt(splitString[1]);
            if ((deleteInt > Duke.taskCounter) || (deleteInt < 1)) {
                throw new DukeException("Oh no! The number you have chosen is not valid!");

            } else {
                return new DeleteCommand(deleteInt);

            }
        } else {
            throw new DukeException("Oh no! You need to choose a number!");

        } 
    }

    private Command parseToDo(String input) throws DukeException {
        String[] splitString = input.split(" ", 2);
        if (splitString.length < 2) {
            throw new DukeException("Oh no! You need to enter the description of the task!");

        } else {
            return new ToDoCommand(splitString[1]);

        }
    }

    private Command parseDeadline(String input) throws DukeException {
        String[] splitString = input.split(" ", 2);
        if (splitString.length < 2) {
            throw new DukeException("Oh no! You need to enter the description of the task!");

        } else {
            return new DeadlineCommand(splitString[1]);

        }
    }

    private Command parseEvent(String input) throws DukeException {
        String[] splitString = input.split(" ", 2);
        if (splitString.length < 2) {
            throw new DukeException("Oh no! You need to enter the description of the task!");

        } else {
            return new EventCommand(splitString[1]);

        }
    }

    private Command parseCheckDate(String input) throws DukeException {
        String[] splitString = input.split(" ", 3);
        if (splitString.length < 3) {
            throw new DukeException("Oh no! You need to enter the date that you want to check!");

        } else {
            return new CheckDateCommand(splitString[2]);
            
        }
    }

    private Command parseCommands() {
        return new commandsCommand();
    }

}