import duke.exception.IncompleteCommandException;
import duke.exception.MissingIndexException;

import java.lang.reflect.Array;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

/**
 *
 */
public class InputReader {

    public InputReader() {
    }

    /**
     * Uses Regex to replace all instances of non digit characters to be parsed as int
     *
     * @param input
     * @return int for the task index
     */
    public int extractTaskIndexNo(String input) throws MissingIndexException {
        String taskIndexString =input.replaceAll("\\D+", "");
        try{
            return Integer.parseInt(taskIndexString) - 1;
        } catch (NumberFormatException e){
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
        String returnString = input.replace("todo ", "").trim();
        if (returnString.equals("")){
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
        String[] returnString = trimAll(input.replace("deadline ", "").split("/by"));
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
        String[] returnString = trimAll(input.replace("event ", "").split("/at"));
        if (returnString.length != 2 || checkNotEmpty(returnString)) {
            throw new IncompleteCommandException();
        }
        return returnString;
    }

    /**
     * Iterates through String array to trim all whitespace.
     * @param input
     * @return String[] with whitespace trimmed
     */
    public String[] trimAll(String[] input){
        for (int i = 0; i < input.length; i++){
            input[i] = input[i].trim();
        }
        return input;
    }

    /**
     * Ensures that after whitespace is trimmed, there is no empty String left
     * @param input
     * @return boolean if there is any empty string
     */
    public boolean checkNotEmpty(String[] input){
        for (int i = 0; i < input.length; i++){
            if (input[i].equals("")){
                return true;
            }
        }
        return false;
    }

    /**
     * Convert string from stored file to ArrayList of elements.
     * @param oldString
     * @return
     */
    public ArrayList<String> parseSavedString(String oldString){
        Pattern taskPattern = Pattern.compile("^\\[([EDT])\\]\\s\\[(.+)\\]\\s(.+)\\s(?:\\([ab][ty]\\:\\s(.+)\\))?$");
        Matcher matcher = taskPattern.matcher(oldString);
        ArrayList<String> totalMatches = new ArrayList<>();
        for (int i=1; i<=matcher.groupCount(); i++){
            try {
                String match = matcher.group(i);
                totalMatches.add(matcher.group(i));
            } catch (IllegalStateException e){
                return totalMatches;
            }
        }
        return totalMatches;
    }

}
