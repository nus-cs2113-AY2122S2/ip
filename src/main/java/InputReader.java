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
    public String extractToDoTask(String input) {
        return input.replace("todo ", "");
    }

    /**
     * Reformats string to remove "deadline " substring from user input, then
     * splits into task name and deadline.
     *
     * @param input
     * @return String array of task and deadline, size 2
     */
    public String[] extractDeadlineTask(String input) {
        String temp = input.replace("deadline ", "");
        return temp.split("/by ");
    }

    /**
     * Reformats string to remove "event " substring from user input, then
     * splits into task name and event time.
     *
     * @param input
     * @return String array of task and event time, size 2
     */
    public String[] extractEventTask(String input) {
        String temp = input.replace("event ", "");
        return temp.split("/at ");
    }

}
