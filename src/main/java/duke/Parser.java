package duke;

import duke.exceptions.EmptyCommandException;
import duke.exceptions.InvalidArgumentException;
import duke.exceptions.InvalidCommandException;

import java.util.Map;
import static java.util.Map.entry;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {

    // Dictionary storing mappings of command -> regex string to extract parameters
    public static final Map<String, Pattern> MAP_COMMAND_FORMAT = Map.ofEntries(
            entry("bye", Pattern.compile("(.?)")),
            entry("list", Pattern.compile("(.?)")),
            entry("mark", Pattern.compile("(\\d+)")),
            entry("unmark", Pattern.compile("(\\d+)")),
            entry("todo", Pattern.compile("(.+)")),
            entry("deadline", Pattern.compile("(.+)\\s\\/by\\s(.+)")),
            entry("event", Pattern.compile("(.+)\\s\\/at\\s(.+)")));

    // Used to extract initial command
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(\\S+)(.*)");

    // Data fields to store command arguments
    private String userCommand;
    private Map<String, String> argumentList;

    /**
     * Initialises this Parser with empty userCommand and empty argumentList
     * Functions as a singleton class (without the appropriate code), instantiate only one
     */
    public Parser() {
        this.userCommand = "";
        argumentList = null;
    }

    /**
     * Parses user input, storing results in this class's userCommand and argumentList fields
     * @param userInput input from user
     */
    public void parseInput(String userInput)  {
        try {
            // Initiate search, while checking if command is empty
            Matcher commandMatcher = COMMAND_FORMAT.matcher(userInput);
            searchCommand(commandMatcher);
            // Check if entered command is valid
            String inputCommand = commandMatcher.group(1);
            parseCommand(inputCommand);
            // Check if arguments are valid
            String inputArguments = commandMatcher.group(2).trim();
            parseArguments(inputCommand, inputArguments);
        } catch (EmptyCommandException e) {
            System.out.println(e.getMessage());
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        } catch (InvalidArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Returns parsed command from user input
     *
     * @return parsed command form user input
     */
    public String getUserCommand() {
        return this.userCommand;
    }

    /**
     * Get all arguments
     *
     * @return command given by user
     */
    public Map<String, String> getArgumentList() {
        return this.argumentList;
    }

    /**
     * Returns boolean indicating if command is bye
     *
     * @return boolean indicating userCommand is "bye"
     */
    public Boolean isBye() {
        return (this.userCommand.equals("bye"));
    }

    /**
     * Checks if the userInput in CommandMatcher matches the regex command format
     * @param commandMatcher the matcher object to search on
     * @throws EmptyCommandException if command is empty
     */
    private void searchCommand(Matcher commandMatcher) throws EmptyCommandException {
        if (!commandMatcher.matches()) {
            throw new EmptyCommandException();
        }
    }

    /**
     * Checks if the command entered is valid
     * @param inputCommand the extracted user command
     * @throws InvalidCommandException if command does not match any known commands
     */
    private void parseCommand(String inputCommand) throws InvalidCommandException {
        Set<String> keys = MAP_COMMAND_FORMAT.keySet();
        Boolean isValidCommand = keys.contains(inputCommand);
        if (!isValidCommand) {
            throw new InvalidCommandException(inputCommand);
        }
    }

    /**
     * Checks if the arguments entered matches the regex for the given command and sets userCommand and argumentList accordingly
     * @param inputArguments the extracted user arguments
     * @throws InvalidArgumentException if argument does not follow format given by input command
     */
    private void parseArguments(String inputCommand, String inputArguments) throws InvalidArgumentException {
        // Get associated regex with inputCommand
        Pattern argumentPattern = MAP_COMMAND_FORMAT.get(inputCommand);
        Matcher argumentMatcher = argumentPattern.matcher(inputArguments);
        if (!argumentMatcher.matches()) {
            throw new InvalidArgumentException(inputCommand);
        }
        this.userCommand = inputCommand;
        switch (inputCommand) {
        case "deadline":
            this.argumentList = Map.ofEntries(
                    entry("", argumentMatcher.group(1).trim()),
                    entry("by", argumentMatcher.group(2).trim()));
            break;
        case "event":
            this.argumentList = Map.ofEntries(
                    entry("", argumentMatcher.group(1).trim()),
                    entry("at", argumentMatcher.group(2).trim()));
            break;
        default:
            this.argumentList = Map.ofEntries(entry("", argumentMatcher.group(1).trim()));
            break;
        }
    }
}