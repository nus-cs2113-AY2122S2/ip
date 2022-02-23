package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Parser {

    //Flags
    public static final String FLAG_DEADLINE = " /by ";
    public static final String FLAG_EVENT = " /at ";

    /**
     * Returns a String array which parses the description and corresponding task parameter.
     * The first element of the string array is the task description and the second element of the
     * string array is the deadline for Deadline task or time period for Event task.
     *
     * @param input command provided by the user
     * @return String array with 2 elements: task description and task parameter
     */
    public static String[] parseDeadlineOrEvent(String input) {
        String command = getCommand(input);
        String[] inputArray;
        if (command.equals(Commands.COMMAND_DEADLINE)) {
            inputArray = input.split(FLAG_DEADLINE);
        } else {
            inputArray = input.split(FLAG_EVENT);
        }
        inputArray[0] = inputArray[0].substring(inputArray[0].indexOf(" "));
        return inputArray;
    }

    /**
     * Returns a String representing the integer of the chosen task.
     * The method is called when user calls the command Mark or Unmark along with the corresponding
     * task number.
     *
     * @param input the mark or unmark command
     * @return String representation of the task number
     * @throws DukeException If Mark or Unmark command follows an invalid syntax
     */
    public static String parseMarkOrUnmarkOrDelete(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        if (inputArray.length != 2) {
            throw new DukeException(Ui.ERROR_INVALID_SYNTAX);
        }
        return inputArray[1];
    }

    /**
     * Returns the first word, or the command, from the input entered by the user.
     *
     * @param input command provided by the user
     * @return command specified by the user
     */
    public static String getCommand(String input) {
        String[] arrayOfInput = input.split(" ", 0);
        return arrayOfInput[0];
    }

    /**
     * Returns description of the Todo task from the Todo command entered by the user.
     *
     * @param input Todo command entered by the user
     * @return description of Todo
     */
    public static String getDescription(String input) {
        return input.substring(input.indexOf(" "));
    }

    /**
     * Prints all the tasks in the list.
     */
    public static void listTasks() {
        if (Ui.taskList.size() == 0) {
            System.out.println(Ui.MESSAGE_NO_TASKS);
        } else {
            for (int i = 0; i < Ui.taskList.size(); i++) {
                System.out.println((i + 1) + ". " + Ui.taskList.get(i));
            }
        }
    }

    /**
     * Returns a formatted String of the corresponding task to be saved in the storage file.
     *
     * @param task the subclass of Task
     * @return the string format needed for the storage file
     */
    public static String getFormattedString(Task task) {
        if (task instanceof Todo) {
            return (Storage.FILE_TODO + " | " + convertStatusToString(task.getIsDone()) + " | "
                    + task.getDescription());
        } else if (task instanceof Deadline) {
            return (Storage.FILE_DEADLINE + " | " + convertStatusToString(task.getIsDone()) + " | "
                    + task.getDescription() + " | " + ((Deadline) task).getDeadline());
        } else {
            return (Storage.FILE_EVENT + " | " + convertStatusToString(task.getIsDone()) + " | "
                    + task.getDescription() + " | " + ((Event) task).getAt());
        }
    }

    /**
     *  Converts the status of the task, whether done or not done to a string (0 or 1).
     *  If task is done, method returns FILE_DONE. If task is undone, method returns FILE_NOT_DONE.
     *
     * @param value status of task
     * @return string representation of status of task
     */
    public static String convertStatusToString(boolean value) {
        if (value) {
            return Storage.FILE_DONE;
        }
        return Storage.FILE_NOT_DONE;
    }

    public static boolean isBye(String command) {
        return command.equals(Commands.COMMAND_BYE);
    }
}
