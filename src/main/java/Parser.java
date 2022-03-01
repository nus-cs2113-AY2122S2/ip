import duke.exception.IncompleteCommandException;
import duke.exception.MissingIndexException;

import java.util.ArrayList;

/**
 *
 */
public class Parser {

    public Parser() {
    }

    /**
     * Uses Regex to replace all instances of non digit characters to be parsed as int
     *
     * @param input
     * @return int for the task index
     */
    public int extractTaskIndexNo(String input) throws MissingIndexException {
        String taskIndexString = input.replaceAll("\\D+", "");
        try {
            return Integer.parseInt(taskIndexString) - 1;
        } catch (NumberFormatException e) {
            throw new MissingIndexException();
        }
    }

    /**
     * Reformats string to remove "todo " substring from user input
     *
     * @param input
     * @return input less "todo " substring
     */
    public String extractToDoTask(String input) throws IncompleteCommandException {
        String returnString = input.replace("todo", "").trim();
        if (returnString.equals("")) {
            throw new IncompleteCommandException();
        }
        return returnString;
    }

    /**
     * Reformats string to remove "deadline " substring from user input, then
     * splits into task name and deadline.
     *
     * @param input
     * @return String array of task and deadline, size 2
     */
    public String[] extractDeadlineTask(String input) throws IncompleteCommandException {
        String[] returnString = trimAll(input.replace("deadline", "").split("/by"));
        if (returnString.length != 2 || checkNotEmpty(returnString)) {
            throw new IncompleteCommandException();
        }
        return returnString;
    }

    /**
     * Reformats string to remove "event " substring from user input, then
     * splits into task name and event time.
     *
     * @param input
     * @return String array of task and event time, size 2
     */
    public String[] extractEventTask(String input) throws IncompleteCommandException {
        String[] returnString = trimAll(input.replace("event", "").split("/at"));
        if (returnString.length != 2 || checkNotEmpty(returnString)) {
            throw new IncompleteCommandException();
        }
        return returnString;
    }

    /**
     * Iterates through String array to trim all whitespace.
     *
     * @param input
     * @return String[] with whitespace trimmed
     */
    public String[] trimAll(String[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = input[i].trim();
        }
        return input;
    }

    /**
     * Ensures that after whitespace is trimmed, there is no empty String left
     *
     * @param input
     * @return boolean if there is any empty string
     */
    public boolean checkNotEmpty(String[] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals("")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Convert string from stored file to ArrayList of elements. If the type tag is recognised, items returned will be in the order:
     * <p>
     * 1. type tag, character 'T', 'D' or 'E'
     * 2. completion string, 'true' or 'false'
     * 3. task information
     * 4. deadline/event time as indicated within the brackets
     *
     * @param oldString
     * @return
     */
    public ArrayList<String> parseSavedString(String oldString) {
        ArrayList<String> returnArray = new ArrayList<>();
        switch (oldString.charAt(1)) {
        case 'T':
            returnArray.add(String.valueOf(oldString.charAt(1))); // for the type tag
            returnArray.add((oldString.charAt(5) == ' ') ? "false" : "true"); // for the completion tag
            returnArray.add(oldString.substring(8));
            break;
        case 'E':
            returnArray.add(String.valueOf(oldString.charAt(1))); // for the type tag
            returnArray.add((oldString.charAt(5) == ' ') ? "false" : "true"); // for the completion tag
            int eventDiv = oldString.indexOf("(at:");
            returnArray.add(oldString.substring(8, (eventDiv - 1)));
            returnArray.add(oldString.substring(eventDiv + 5, (oldString.length() - 1)));
            break;
        case 'D':
            returnArray.add(String.valueOf(oldString.charAt(1))); // for the type tag
            returnArray.add((oldString.charAt(5) == ' ') ? "false" : "true"); // for the completion tag
            int deadlineDiv = oldString.indexOf("(by:");
            returnArray.add(oldString.substring(8, (deadlineDiv - 1)));
            returnArray.add(oldString.substring(deadlineDiv + 5, (oldString.length() - 1)));
            break;
        default:
            return returnArray;
        }
        return returnArray;
    }
}
