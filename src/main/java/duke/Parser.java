package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;

import duke.exceptions.EmptyCommandException;
import duke.exceptions.InvalidCommandException;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {

    // Used to extract initial command
    private static final Pattern COMMAND_FORMAT = Pattern.compile("(\\S+)(.*)");

    /**
     * Empty Parser Constructor
     */
    public Parser() {

    }

    /**
     * Parses for user command and returns the proper Command object.
     * @param userInput input from user
     */
    public Command parseInput(String userInput)  {
        try {
            // Initiate search, while checking if command is empty
            Matcher commandMatcher = COMMAND_FORMAT.matcher(userInput);
            checkForCommand(commandMatcher);
            // Create the appropriate Command object. Pass in unparsed arguments, will be handled by individual Command classes.
            String inputCommand = commandMatcher.group(1);
            String inputArguments = commandMatcher.group(2).strip();
            return createCommand(inputCommand, inputArguments);
        } catch (EmptyCommandException e) {
            System.out.println(e.getMessage());
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Checks if the userInput in CommandMatcher matches the regex command format
     * @param commandMatcher the matcher object to search on
     * @throws EmptyCommandException if command is empty
     */
    private void checkForCommand(Matcher commandMatcher) throws EmptyCommandException {
        if (!commandMatcher.matches()) {
            throw new EmptyCommandException();
        }
    }

    private Command createCommand(String inputCommand, String inputArguments) throws InvalidCommandException {
        HashMap<String, String> parsedArguments;
        switch(inputCommand) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            parsedArguments = argumentParser(inputArguments);
            return new MarkCommand(parsedArguments);
        case "unmark":
            parsedArguments = argumentParser(inputArguments);
            return new UnmarkCommand(parsedArguments);
        case "todo":
            parsedArguments = argumentParser(inputArguments);
            return new TodoCommand(parsedArguments);
        case "deadline":
            parsedArguments = argumentParser(inputArguments);
            return new DeadlineCommand(parsedArguments);
        case "event":
            parsedArguments = argumentParser(inputArguments);
            return new EventCommand(parsedArguments);
        }
        throw new InvalidCommandException(inputCommand);
    }

    private HashMap<String, String> argumentParser(String inputArguments) {
        HashMap<String, String> parsedArguments = new HashMap<String, String>();
        String parameterName = "";
        String parameter = "";
        String[] argumentArray = inputArguments.split(" ", 0);
        for (String argument: argumentArray) {
            if (argument.matches("^\\/\\w+$")) {
                parameter = parameter.strip();
                parsedArguments.put(parameterName, parameter);
                parameterName = argument;
                parameter = "";
                continue;
            }
            parameter += " "+argument;
        }
        parsedArguments.put(parameterName, parameter.strip());
        return parsedArguments;
    }
}