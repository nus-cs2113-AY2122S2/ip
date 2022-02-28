package duke;

/**
 * This class is to parse the various parameters of the command
 *
 */

public class Parser {
    /**
     * Returns the first word of command (before the first space)
     *
     * @param command command String to perform extraction on
     * @return first word of command
     *
     */
    public static String getFirstWordOfCommand(String command) {
        int spaceIndex = command.indexOf(" ");
        if (spaceIndex == -1) {
            return command;
        }
        String firstWordOfCommand = command.substring(0, spaceIndex);
        return firstWordOfCommand.trim();
    }
    /**
     * Returns a Task from task list with the specified index.
     *
     * @param command command String to get task details from
     * @return task details
     *
     */
    public static String getTaskDetailsForFind(String command) {
        int spaceIndex = command.indexOf(" ");
        String taskDetail = command.substring(spaceIndex);
        return taskDetail.trim();
    }
    /**
     * Returns task number from the input string for mark and unmark section
     *
     * @param input command String to task number from
     * @return task number
     *
     */
    public static int getTaskNumberArgument(String input) {
        int spaceIndex = input.indexOf(" ");
        String taskNum = input.substring(spaceIndex + 1);
        return Integer.parseInt(taskNum.trim());
    }

}
