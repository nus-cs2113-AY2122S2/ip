/**
 * Parses user input
 */

public class Parser {

    /**
     * Parses user input into command for execution.
     * If the user input is empty, DukeException or IndexOutOfBoundsException will be thrown.
     * It will print a corresponding error message to the exception caught.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public static String parseCommand(String userInput) {
        String command = userInput.trim();
        try {
            if (command.isEmpty()) {
                throw new DukeException(Ui.EMPTY_INPUT_MESSAGE);
            }
            command = userInput.split(" ")[0];
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Ui.EMPTY_INPUT_MESSAGE);
        }
        return command;
    }

    /**
     * Parses user input to get the task index for execution.
     * If the index in the user input is less than 0, NumberFormatException will be thrown.
     * It will print a corresponding error message to the exception caught.
     *
     * @param userInput full user input string
     * @return the task index based on the user input
     */
    public static int parseTaskIndex(String userInput) {

        int index = 0;

        try {
            index = Integer.parseInt(
                    userInput.substring(userInput.indexOf(" ") + 1)) - 1;
            if (index < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println(Ui.ILLEGAL_INDEX_MESSAGE);
        }
        return index;
    }

    /**
     * Parses user input to get the due date for deadline and event type of task.
     * If the date in the user input is empty, StringIndexOutOfBoundsException or DukeException will be thrown.
     * It will print a corresponding error message to the exception caught.
     *
     * @param separator to split the task's description and due date. ("/by" for deadline and "/at" for event)
     * @param userInput full user input string
     * @return the due date for a task
     */
    public static String parseDate(String separator, String userInput) {

        String date = "";

        try {
            date = userInput.substring(userInput.indexOf(separator) + 4);
            date = date.trim();
            if (date.isEmpty()) {
                throw new DukeException(Ui.EMPTY_DATE_MESSAGE);
            }
        } catch (StringIndexOutOfBoundsException e) {
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }

    /**
     * Parses user input to get the description of a task.
     * If the description in the user input is empty, IndexOutOfBoundsException or DukeException will be thrown.
     * It will print a corresponding error message to the exception caught.
     *
     * @param separator to split the task's description and due date. ("/by" for deadline and "/at" for event)
     * @param userInput full user input string
     * @param taskType type of a task such as todo, deadline or event
     * @return the description of a task based on the user input
     */
    public static String parseDescription(String separator, String userInput, String taskType) {

        String description = "";

        try {
            if (taskType.equalsIgnoreCase(TaskManager.TODO_COMMAND) ||
                    taskType.equalsIgnoreCase(Command.FIND_COMMAND)) {
                description = userInput.substring(userInput.indexOf(" ") + 1);
            }
            else {
                description = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf(separator));
            }
            description = description.trim();

            if (description.isEmpty()) {
                throw new DukeException(Ui.EMPTY_DESCRIPTION_MESSAGE);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
        }
        return description;
    }

    /**
     * Parses data saved in the local machine to get the task type for loading data.
     * If the task type in the data is empty, IndexOutOfBoundsException will be thrown.
     * It will print a corresponding error message to the exception caught.
     *
     * @param data data in a text file
     * @return type of task based on the data
     */
    public static String parseSavedTaskType(String data) {
        String taskType = "";
        try {
            taskType = data.substring(data.indexOf("[") + 1, data.indexOf("]"));
            if (taskType.trim().isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Ui.LOAD_FAILED_MESSAGE);
        }
        return taskType;
    }

    /**
     * Parses data saved in the local machine to get the description of a task for loading data.
     * If the description in the data is empty, DukeException will be thrown.
     * It will print a corresponding error message to the exception caught.
     *
     * @param data data in a text file
     * @param separator to split the task's description and due date. ("(by" for deadline and "(at" for event)
     * @param taskType type of a task such as T, D or E
     * @return the description of a task based on the data
     */
    public static String parseSavedTaskDescription(String data, String separator, String taskType) {
        String description = "";
        try {
            switch (taskType) {
            case "T":
                description = data.substring(7);
                break;
            case "D":
            case "E":
                description = data.substring(7, data.indexOf(separator));
                break;
            default:
                throw new DukeException(Ui.LOAD_FAILED_MESSAGE);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return description;
    }

    /**
     * Parses data saved in the local machine to get the due date of a task for loading data.
     * If the due date in the data is empty, DukeException will be thrown.
     * It will print a corresponding error message to the exception caught.
     *
     * @param data data in a text file
     * @param separator to split the task's description and due date. ("(by" for deadline and "(at" for event)
     * @return the due date for a task based on the data
     */
    public static String parseSavedTaskDate(String data, String separator) {
        String date = "";
        try {
            date = data.substring(data.indexOf(separator) + 5, data.indexOf(")"));
            if (date.trim().isEmpty()) {
                throw new DukeException(Ui.LOAD_FAILED_MESSAGE);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }


}
