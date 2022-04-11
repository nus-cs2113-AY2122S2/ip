package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

    



 /**
 * Deals with making sense of the user command.
 */
public class Parser {

    private static Ui ui;

    public Parser() {
        ui = new Ui();
    }
     /**
     * Takes in the user input as a string, split it into sections
     * stored in a string array and return the first index to be the
     * user command.
     *
     * @param inputCommand The string of the user input
     * @return The command type of the input
     */
    public static String parseCommandFromString(String inputCommand) {
        String[] command = inputCommand.split(" ");
        return command[0];
    }

    /**
     * Takes in the user input as a string, split it into sections
     * stored in a string array and return the index which consist
     * of the item in the list the user wants to mark as done.
     *
     * @param inputCommand The string of user input
     * @return The item number on the list the user wants to mark as done
     */
    public static int parseMarkIndexFromString(String inputCommand) {
        String[] commandArr = inputCommand.split(" ");
        int markNum = Integer.parseInt(commandArr[1]) - 1;
        return markNum;
    }

    /**
     * Takes in the user input as a string, split it into sections
     * stored in a string array and return the index which consist
     * of the item in the list the user wants to mark as not done.
     *
     * @param inputCommand The string of user input
     * @return The item number on the list the user wants to mark as not done
     */
    public static int parseUnMarkIndexFromString(String inputCommand) {
        String[] commandArr = inputCommand.split(" ");
        int UnMarkNum = Integer.parseInt(commandArr[1]) - 1;
        return UnMarkNum;
    }

    /**
     * Takes in the user input as a string, split it into sections
     * stored in a string array and return the index which consist
     * of the item in the list the user wants to delete.
     *
     * @param inputCommand The string of user input
     * @return The item number on the list the user wants to delete
     * @throws IndexOutOfBoundsException If delete index is empty or out of range
     */
    public static int parseDeleteIndexFromString(String inputCommand) {
        try {
            String[] commandArr = inputCommand.split(" ");
            int deleteIndex = Integer.parseInt(commandArr[1]) - 1;
            return deleteIndex;
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidDeleteIndex();
        }
        return 0;
    }

    /**
     * Takes in the user input as a string and split the string
     * into sections to take only the task type of the command.
     *
     * @param inCommand The user input string
     * @return The task type of the user command
     */
    public String parseTaskTypeFromString(String inCommand) {
        String[] sentenceArr = inCommand.split(" ", 2);
        return sentenceArr[0];
    }

    /**
     * Takes in the user input as a string and split it into
     * a string array.
     *
     * @param inCommand The user input as a string
     * @return The string array consisting of the user commands
     */
    public String[] parseTaskDescriptionFromString(String inCommand) {
        String[] sentenceArr = inCommand.split(" ", 2);
        return sentenceArr;
    }

    /**
     * Takes in the task description for the todo task
     * and return the action of the task.
     *
     * @param taskDescription The todo task description
     * @return The action of the task
     */
    public String parseToDoActionFromDescription(String[] taskDescription) {
        return taskDescription[1];
    }

    /**
     * Takes in the task description for the deadline task
     * and return the action of the task.
     *
     * @param taskDescription The deadline task description
     * @return The action of the task
     */
    public String[] parseDeadLineActionFromDescription(String[] taskDescription) {
        return taskDescription[1].split("/by", 2);
    }

    /**
     * Takes in the task description for the event task
     * and return the action of the task.
     *
     * @param taskDescription The event task description
     * @return The action of the task
     */
    public String[] parseEventsActionFromDescription(String[] taskDescription) {
        return taskDescription[1].split("/at", 2);
    }

    /**
     * Takes in the read line from the local file to be loaded
     * into the list when the program begins.
     *
     * @param line The read line from the local file
     * @return The string array of the read line split by commands
     */
    public String[] parseLineFromFile(String line) {
        return line.split("-", 3);
    }

     /**
      * Takes in the user input and parse the keyword from it
      *
      * @param inputCommand The user input
      * @return The keyword the user wants to find
      * @throws IndexOutOfBoundsException If the keyword is empty
      */
    public String getKeywordFromString(String inputCommand) {
        try {
            String[] commandArr = inputCommand.split(" ");
            return commandArr[1];
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidKeywordError();
        }
        return "";
    }

    public LocalDate parseDateFormatFromString(String dateInString)  {
        LocalDate date = LocalDate.now();
        try {
            date = LocalDate.parse(dateInString.trim());
        } catch (DateTimeParseException invalidDate) {
            ui.printInvalidDateException();
        }
        return date;
    }
}
