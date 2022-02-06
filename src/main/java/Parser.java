import java.util.Map;
import static java.util.Map.entry;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Parser {

    // Dictionary storing mappings of command -> regex string to extract parameters
    public static final Map<String, Pattern> MAP_COMMAND_FORMAT = Map.ofEntries(
            entry("bye", Pattern.compile("(.?)")),
            entry("list", Pattern.compile("(.?)")),
            entry("mark", Pattern.compile("(\\d+)")),
            entry("unmark", Pattern.compile("(\\d+)")),
            entry("todo", Pattern.compile("(\\S+)")),
            entry("deadline", Pattern.compile("(.*)\\/by\\s(.*)")),
            entry("event", Pattern.compile("(.*)\\/at\\s(.*)")));

    // Used to extract initial command
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(\\S+)(.*)");

    // Data fields to store command arguments
    protected String userCommand;
    protected Map<String, String> argumentList;

    /**
     * Initialises this Parser with empty userCommand and empty argumentList
     */
    public Parser() {
        this.userCommand = "";
        argumentList = null;
    }

    /**
     * Parse user input.
     * Sets userCommand to the command in userInput
     * Sets argumentList on the arguments passed through userInput
     * argumentList is built based off userCommand
     * userCommand and argumentList will be set to empty if the command given does not match regex
     *
     * @param userInput user input to be parsed
     * @return boolean indicating valid argument
     */
    public Boolean parseInput(String userInput) {
        // Get the command and set userCommand
        Matcher commandMatcher = COMMAND_FORMAT.matcher(userInput);
        // if empty (todo improve checks)
        if (!commandMatcher.find()) {
            this.userCommand = "";
            this.argumentList = null;
            return false;
        }
        userCommand = commandMatcher.group(1);
        // Set arguments based off userCommand
        String arguments = commandMatcher.group(2).trim();
        Pattern argumentFormat = MAP_COMMAND_FORMAT.get(userCommand);
        Matcher argumentMatcher = argumentFormat.matcher(arguments);
        if (!argumentMatcher.find()) {
            this.userCommand = "";
            this.argumentList = null;
            return false;
        }
        // Half-baked argument parser, not extensible past iP
        switch (userCommand) {
        case "deadline":
            argumentList = Map.ofEntries(
                    entry("", argumentMatcher.group(1).trim()),
                    entry("by", argumentMatcher.group(2).trim()));
            break;
        case "event":
            argumentList = Map.ofEntries(
                    entry("", argumentMatcher.group(1)),
                    entry("at", argumentMatcher.group(2).trim()));
            break;
        default:
            argumentList = Map.ofEntries(entry("", argumentMatcher.group(1)));
            break;
        }
        return true;
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
}