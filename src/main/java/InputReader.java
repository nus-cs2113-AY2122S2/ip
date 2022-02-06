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
    public int extractTaskIndexNo(String input) {
        return Integer.parseInt(input.replaceAll("\\D+", "")) - 1;
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

}
